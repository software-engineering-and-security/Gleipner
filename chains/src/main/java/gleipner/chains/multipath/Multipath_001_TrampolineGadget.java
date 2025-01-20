package gleipner.chains.multipath;
        import gleipner.core.annotations.InterProcedural;
        import gleipner.core.GleipnerObject;
        public class Multipath_001_TrampolineGadget extends GleipnerObject {
public Multipath_001_LinkGadget1 linkGadget1;
public Multipath_001_LinkGadget2 linkGadget2;
public Multipath_001_LinkGadget3 linkGadget3;
public Multipath_001_LinkGadget4 linkGadget4;
public Multipath_001_LinkGadget5 linkGadget5;
public Multipath_001_LinkGadget6 linkGadget6;
public Multipath_001_LinkGadget7 linkGadget7;
public Multipath_001_LinkGadget8 linkGadget8;
public Multipath_001_LinkGadget9 linkGadget9;
public Multipath_001_LinkGadget10 linkGadget10;
public int choice;

            public Multipath_001_TrampolineGadget() {
            }

            public Multipath_001_TrampolineGadget(Multipath_001_LinkGadget1 linkGadget1, Multipath_001_LinkGadget2 linkGadget2, Multipath_001_LinkGadget3 linkGadget3, Multipath_001_LinkGadget4 linkGadget4, Multipath_001_LinkGadget5 linkGadget5, Multipath_001_LinkGadget6 linkGadget6, Multipath_001_LinkGadget7 linkGadget7, Multipath_001_LinkGadget8 linkGadget8, Multipath_001_LinkGadget9 linkGadget9, Multipath_001_LinkGadget10 linkGadget10, int choice) {
                this.linkGadget1 = linkGadget1;
                this.linkGadget2 = linkGadget2;
                this.linkGadget3 = linkGadget3;
                this.linkGadget4 = linkGadget4;
                this.linkGadget5 = linkGadget5;
                this.linkGadget6 = linkGadget6;
                this.linkGadget7 = linkGadget7;
                this.linkGadget8 = linkGadget8;
                this.linkGadget9 = linkGadget9;
                this.linkGadget10 = linkGadget10;
                this.choice = choice;
            }

            @Override
        @InterProcedural(InterProcedural.VIRTUAL_INVOKE)
        public int hashCode() {
            switch (choice) {    
        case 1:
linkGadget1.linkMethod();
case 2:
linkGadget2.linkMethod();
case 3:
linkGadget3.linkMethod();
case 4:
linkGadget4.linkMethod();
case 5:
linkGadget5.linkMethod();
case 6:
linkGadget6.linkMethod();
case 7:
linkGadget7.linkMethod();
case 8:
linkGadget8.linkMethod();
case 9:
linkGadget9.linkMethod();
case 10:
linkGadget10.linkMethod();
default:
            break;
        }
        return super.hashCode();
        }
    }    
        