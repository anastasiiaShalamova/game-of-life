package game_of_life;
import java.util.Arrays;

class CircularQueue {
    private Cell[] array;
    private int front;
    private int rear;
    private int capacity;

    public CircularQueue(int capacity) {
        this.capacity = capacity;
        this.array = new Cell[capacity];
        this.front = this.rear = -1;
    }

    public boolean isEmpty() {
        return front == -1;
    }

    public void enqueue(Cell cell) {
        if ((rear + 1) % capacity == front) {
            resize();
        }

        if (isEmpty()) {
            front = rear = 0;
        } else {
            rear = (rear + 1) % capacity;
        }

        array[rear] = cell;
    }

    public Cell dequeue() {
    	
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }

        Cell cell = array[front];

        if (front == rear) {
            front = rear = -1;
        } else {
            front = (front + 1) % capacity;
        }

        return cell;
    }

    private void resize() {
    	
        int newSize = capacity * 2;
        array = Arrays.copyOf(array, newSize);

        if (front > rear) {
            System.arraycopy(array, 0, array, capacity, rear + 1);
            rear += capacity;
        }

        capacity = newSize;
    }
}