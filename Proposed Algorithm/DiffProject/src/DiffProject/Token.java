package DiffProject;

import org.w3c.dom.Node;

/**
 * <p>Title: XML Diff Project</p>
 * <p>Description: This class implements the Token concept.</p>
 * <p>Copyright: Copyright (c) 2007</p>
 * @author Samad Paydar
 * @version 1.0
 */
public class Token {
    public Node node;
    public int index;
    public int level;

    public Token(Node theNode, int theIndex, int theLevel) {
        node = theNode;
        index = theIndex;
        level = theLevel;
    }

/*    public void println() {
        if(this.node == null) return;
        String result = "";
        result += "nodeIndex: "+ index + " nodeLevel: "+ level;
        switch (this.node.getNodeType()) {
          case Node.ELEMENT_NODE:
            result += " nodeName: " + this.node.getNodeName();
            break;
          case Node.TEXT_NODE:
            result += " nodeValue: " + this.node.getNodeValue();
            break;
        }
        System.out.println(result);
    }
*/
}
