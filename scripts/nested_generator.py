import sys

TRIGGER_TEMPLATE = '''package gleipner.chains.taint;
import gleipner.chains.taint.nested.Nested;
import gleipner.core.GleipnerObject;

public class Taint_$INDEX_TriggerGadget extends GleipnerObject {

    $STATIC

    $PROPS

    @Override
    public int hashCode() {
        
        $BODY
        
        return super.hashCode();
    }
}
'''


class Template_Fields:
    INDEX = "$INDEX"
    STATIC = "$STATIC"
    OBJECT_PROPERTIES = "$PROPS"
    METHOD_BODY = "$BODY"


def nested(depth: int, index: int):
    body = "Nested n1 = nested.get(0);\n"
    body += "Nested n2 = nested.get(1);\n"

    for i in range(depth):
        body += "if (n2.equals(n2.get(0))) return 0;\n"
        body += "if (n1.equals(n1.get(0))) return 0;\n"
        body += "n1 = n1.get(0);\n"
        body += "n2 = n2.get(0);\n"
        body += "if (!n1.equals(n2)) return 0;\n"

    body += "nested.get(0).invoke(nested.get(2).invoke(null));\n"

    trigger = TRIGGER_TEMPLATE
    trigger = trigger.replace(Template_Fields.METHOD_BODY, body)
    trigger = trigger.replace(Template_Fields.STATIC, "")
    trigger = trigger.replace(Template_Fields.INDEX, f'{index:03}')
    trigger = trigger.replace(Template_Fields.OBJECT_PROPERTIES, "public Nested n1;\npublic Nested n2;\n")
    return trigger


def test_nested(depth: int, index: int):
    body = "Nested root = new Nested(3);\n"
    body += "NestedSink sink = new NestedSink(new SinkGadget(), 1);\n"
    body += "root.set(0, sink);\n"
    body += "root.set(1, sink);\n"
    body += "root.set(2, new NestedString());\n"

    for i in range(depth):
        body += f"Nested dummy{i} = new Nested(1);\n"
        if i == 0:
            body += f"sink.set(0, dummy{i});\n"
        else:
            body += f"dummy{i - 1}.set(0, dummy{i});\n"

    body += f"Taint_{index:03}_TriggerGadget tg = new Taint_{index:03}_TriggerGadget();"

    body += '''
    tg.nested = root;
        triggerGadget.o = tg;
        Serializer.serializeDeserialize(triggerGadget);
        Assert.assertTrue(Controller.isChainSuccess);    
    '''
    return body


def flat(cnt: int, index: int):
    return


print(nested(int(sys.argv[1]), 6))
print(test_nested(int(sys.argv[1]), 6))
