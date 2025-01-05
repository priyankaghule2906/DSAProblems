package interviewquestions;


import java.util.Comparator;
import java.util.PriorityQueue;

/* CrowdStrike
* QueryRequest {

String uniqId;
Long severity;
String data;

}
Design a data structure to implement getNext() and put() method such that getNext() should give the next QueryRequest according to highest severity and put should insert the QueryRequest data
*
* */
public class Main {

    public static void main(String[] args) {
        QueryProcessor processor = new QueryProcessor();

        processor.put(new QueryRequest("1", 5L, "Data 1"));
        processor.put(new QueryRequest("2", 10L, "Data 2"));
        processor.put(new QueryRequest("3", 3L, "Data 3"));

        System.out.println("Fetching QueryRequests by severity:");
        while (!processor.isEmpty()) {
            System.out.println(processor.getNext());
        }
    }

}
class QueryRequest {
    String uniqId;
    Long severity;
    String data;

    // Constructor
    public QueryRequest(String uniqId, Long severity, String data) {
        this.uniqId = uniqId;
        this.severity = severity;
        this.data = data;
    }

    @Override
    public String toString() {
        return "QueryRequest{" +
                "uniqId='" + uniqId + '\'' +
                ", severity=" + severity +
                ", data='" + data + '\'' +
                '}';
    }
}

class QueryProcessor {

    private PriorityQueue<QueryRequest> maxHeap;

    public QueryProcessor(){
        maxHeap = new PriorityQueue<>(Comparator.comparingLong((QueryRequest q) -> q.severity).reversed());
    }

    void put(QueryRequest queryRequest){
        maxHeap.offer(queryRequest);
    }

    public QueryRequest getNext(){
        return maxHeap.poll();
    }

    public boolean isEmpty() {
        return maxHeap.isEmpty();
    }

}
