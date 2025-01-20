import os
import sys

FP_GADGETS_TABBY = ["java.util.HashSet: boolean add(java.lang.Object)", "java.util.HashSet: void readObject(java.io.ObjectInputStream)", "<java.io.ObjectInputStream: java.lang.Object readObject()>"]


file_path = sys.argv[1]
new_content = ""

target_gadget = "dummy"

if sys.argv[2] == "aspectj":
    target_gadget = "SimpleCache$StoreableCachingMap: java.lang.Object put"
elif sys.argv[2] == "cc":
    target_gadget = "<gleipner.chains.ysoserial.cc1.InvokerTransformer: java.lang.Object transform(java.lang.Object)>"

with open(file_path, "r") as file:
    for line in file.readlines():
        line = line.strip()
        if "[" in line:
            continue
        if "]" in line:
            if target_gadget in new_content:
                do_print = True
                for fp in FP_GADGETS_TABBY:
                    if fp in new_content:
                        do_print = False
                if do_print:
                    print(new_content)
            new_content = ""
            continue

        if line != "":
            new_content += line + "\n"
