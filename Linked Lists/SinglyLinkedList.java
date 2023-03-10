// implements classic data structure of a singly-linked list, a generic datatype

public class SinglyLinkedList<E>
{
    private int length;
    private Node<E> head;
    private Node<E> tail;

    // internal nested node class
    // building block of the list
    private static class Node<E>
    {
        private E element;
        private Node<E> next;

        public Node(E data, Node<E> n)
        {
            element = data;
            next = n;
        }

        public E getElement() 
        {
            return element;
        }

        public Node<E> getNext()
        {
            return next;
        }

        public void setNext(Node<E> n)
        {
            next = n;
        }
    }

    // singlyLilnkedList class
    public SinglyLinkedList()
    {

    }

    public int size()
    {
        return length;
    }

    public boolean isEmpty()
    {
        return length == 0;
    }

    // returns the data from the first node in the list, null if doesnt exist
    public E first()
    {
        if (isEmpty())
            return null;
        else
            return head.getElement();
    }

    // returns the data from the last node in the list, null if doesnt exist
    public E last()
    {
        if (isEmpty())
            return null;
        else
            return tail.getElement();
    }

    // adds new element to the head of the list
    // and increases the length of the list by one
    public void addFirst(E data)
    {
        head = new Node<>(data,head);
        length++;
        if (length == 0)
            tail = head;
    }

    // adds a new element to the tailof the list and increases length by one
    public void addLast(E data)
    {
        // create temp node refrence with the data at null
        Node<E> temp = new Node<>(data,null);

        // if the list is empty, point head at the temp noce
        if (length == 0)
        {
            head = temp;
        }
        // otherwise, set the tails next refrence to the new node
        else
        {
            tail.setNext(temp);
        }
        // set the tail to the temp node
        tail= temp;
        // incremenet length by 1
        length++;
    }

    public E removeFirst()
    {
        // if empty, theres nothing to remove
        if (length == 0)
        {
            return null;
        }
        // store the first node's data that we are removing
        E data = head.getElement();
        // reassign the head
        head = head.getNext();
        // if the list has no nodes now, set the tail to null

        length--;

        if (length == 0)
        {
            tail = null;
        }
        return data;
    }

    @Override
    public String toString()
    {
        String result="[";

        Node<E>temp=head;

        while(temp!=null)
        {
            result+=temp.getElement() +"->";
            temp=temp.getNext();
        }

        return result.substring(0,result.length()) + "]";
    }


    // public void reverse()
    // {

    //     // 1. if the length is > 1
    //     if (length > 1)
    //     {
    //         // 2. create a Node reference called reversed and set to head
    //         Node<E> reversed = head;
    //         // 3. set tail to reversed
    //         tail = reversed;
    //         // 4. advance head to the next node in the list
    //         // which we can do now that reversed points at the old head
    //         // then set tail's next to null (after which we will ignore tail)
    //         head = head.getNext();
    //         // 5. create a temp node called next so that we can move
    //         // head without losing downstream list nodes
    //         Node<E> next;
    //         // 6. while head is not null
    //         while (head != null)
    //         {
    //             //   6a. set next to head
    //             next = head;
    //             //   6b. advance head to its next node
    //             head = head.getNext();
    //             //   6c. point the new node at the head of the reversed list 
    //             next.setNext(reversed);
    //             //   6d. point reversed to the newly inserted node
    //             reversed = next;
    //             // 7. set head to reversed and reversed to null

    //         }
    //         head = reversed;
    //         reversed = null;
    //     }
    // }


    public SinglyLinkedList<E> concat(SinglyLinkedList<E> other)
    {
        // create a new, empty SLL
        SinglyLinkedList<E> L = new SinglyLinkedList<E>();
        // 2. create a Node reference called temp and set to this.head
        // (we will use this to traverse this list and ad nodes to the 
        // END of the new list)
        Node<E> temp = this.head;
        // 3. while temp is not null
        while (temp != null)
        {
        //   3a. add temp's element at the END of the new list
            L.addLast(temp.getElement());
        //   3b. advance temp to the next node
            temp = temp.getNext();
        }
        // 4. set temp to other.head
        temp = other.head;
        // 5. while temp is not null
        while (temp != null)
        {
        //   5a. add temp's element to the END of the new list
            L.addLast(temp.getElement());
        //   5b. advance temp to the next node
            temp = temp.getNext();
        }
        // 6. return the new list
        return L;

    }
    @Override
    public boolean equals(Object other)
    {
        // if other is null --> false
        if (other == null)
            return false;
        
        // checkif the same class
        if (getClass() != other.getClass())
            return false;
        
        // at this poimnt, we know that other is a SLL<E>
        // turn object into a SLL
        SinglyLinkedList o = (SinglyLinkedList) other;

        // start by checking if their lengths are the same
        if (length != o.size())
            return false;
        
        // now we need to compare the to
        // create node rrefrences for both heads of each list
        Node curA = this.head;
        Node curB = o.head;
        // loop thorugh until one of them arent the same
        while (curA != null)
        {
            if (!(curA.getElement().equals(curB.getElement())))
                return false;
            curA = curA.getNext();
            curB = curB.getNext();
        }
        return true;
    }

    public int getSize()
    {
        int count = 1;
        Node curr = this.head;
        while (curr.getNext() != null)
        {
            count++;
            curr = curr.getNext();
        }
        return count;
    }

}