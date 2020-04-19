package DiffProject;

import java.util.ArrayList;

/**
 * <p>Title: XML Diff Project</p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2007</p>
 *
 * <p>Company: </p>
 *
 * @author Samad Paydar
 * @version 1.0
 */
public class ChunkTable {
    public ArrayList chunks = null;

    public ChunkTable(int size) {
        chunks = new ArrayList();
        Chunk chunk = new Chunk(0, size);
        chunks.add(chunk);
    }

    public void splitFromIndex(int index) {
        int chunkIndex = findContainerChunk(index);
        //System.out.println(chunkIndex);
        Chunk chunk = (Chunk) chunks.get(chunkIndex);
        if(index==chunk.endIndex) return;
        Chunk newChunk = new Chunk(index+1, chunk.endIndex);
        chunk.endIndex = index;
        chunks.add(chunkIndex+1, newChunk);
    }

    public int findContainerChunk(int index) {
        int start= 0;
        int end= chunks.size()-1;
        int m;
        Chunk temp = (Chunk) chunks.get(end);
        if(index >= temp.startIndex && index <= temp.endIndex) return end;
        while(true) {
             m = (end + start) >> 1;
            temp = (Chunk) chunks.get(m);
            if (index >= temp.startIndex && index <= temp.endIndex)
                return m;
            else if(index<temp.startIndex) {
                end = m-1;
            } else if(index>temp.endIndex) {
                start = m+1;
            }
        }
    }
}
