package p1;

public class App {
    public static void main(String[] args) {
        // 자료구조
        String[] names = new String[5];    
        MyLinkedList<String>list = new MyLinkedList<>();
        list.add("한조1");
        list.add("한조2");
        list.add("한조3");
        list.add("한조4");
        list.add("한조5");
        list.add("한조6");
        list.printAll();
        list.deleteValue("한조5");
        list.printAll();
        list.deleteIndex(1);
        list.insert("끼어드는 값", 2);
        list.printAll();
        System.out.println("==========");
        System.out.println(list.get(3));
    }
}

// LinkedList 설명

class Node<V>{
    private V value;
    private Node<V> next;

    public V getValue() {
        return value;
    }
    public void setValue(V value) {
        this.value = value;
    }
    public Node<V> getNext() {
        return next;
    }
    public void setNext(Node<V> next) {
        this.next = next;
    }

}

class MyLinkedList<E>{
    // fist 변수가 필요함 (첫번째 주소를 알려주는 값이 필요함)
    Node<E> firstNode = null;
    int count = 0;
    
    // Node에 값 추가 하기
    void add (E value){
        Node<E> newNode = new Node<>();
        newNode.setValue(value);
        
        // 첫번째
        if(firstNode == null){
            firstNode = newNode;
            count++;
            return;
        }

        Node<E> tempNode = firstNode;
        while(tempNode.getNext() != null){
            tempNode = tempNode.getNext();
        }
        tempNode.setNext(newNode);
        count++;
    }
    
    void printAll(){
        Node<E> tempNode = firstNode;

        if(tempNode == null){
            System.out.println("리스트가 비어있습니다.");
            return;
        }
        
        System.out.println("========");
        while (tempNode != null) {
            System.out.println(tempNode.getValue());
            tempNode = tempNode.getNext();
            
        }
    }

    
    E get(int index){
       
        Node<E> iteratorNode = firstNode;
        for(int i = 0; i < index; i++){
            iteratorNode = iteratorNode.getNext();
        }

        return iteratorNode.getValue();
    }

    void insert(E value, int index){
        Node<E> newNode = new Node<>();
        newNode.setValue(value);

        Node<E> iteratorNode = firstNode;
        for(int i = 0; i < index - 1; i++){
            iteratorNode = iteratorNode.getNext();
        }
        newNode.setNext(iteratorNode.getNext());
        iteratorNode.setNext(newNode);
        count++;
    }
    void deleteIndex(int index){
        if(firstNode == null){
            System.out.println("리스트가 비어있습니다.");
            return;
        }

        Node<E> iteratorNode = firstNode;
        for(int i = 0; i < index - 1; i++){
            iteratorNode = iteratorNode.getNext();
        }
        Node<E> nodeToDelete = iteratorNode.getNext();
        iteratorNode.setNext(nodeToDelete.getNext());
        count--;
    }

    void deleteValue(String value){
        if (firstNode == null) {
            System.out.println("리스트가 비어있습니다.");
            return;
        }

        if(firstNode.getValue().equals(value)){
            firstNode = firstNode.getNext();
            return;
        }

        Node<E> previousNode = null;
        Node<E> currentNode = firstNode;

        while(currentNode != null && !currentNode.getValue().equals(value)){
            previousNode = currentNode;
            currentNode = currentNode.getNext();
        }

        if(currentNode == null){
            System.out.println("해당 값을 가진 노드를 찾을 수 없습니다.");
            return;

        }
        previousNode.setNext(currentNode.getNext());
        count--;
    }
}