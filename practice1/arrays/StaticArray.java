package course.task;


/**
 * Обертка над статическим массивом
 */
public class StaticArray implements Array {

    protected int[] array;

    public StaticArray(int[] a) {
        this.array = new int[a.length];
        System.arraycopy(a, 0, this.array, 0, a.length);
    }

    @Override
    public int size() {
        // TODO: вернуть длину массива
        return array.length;
    }

    @Override
    public boolean contains(int value) {
        // TODO: проверить, что элемент есть в массиве
        for (int item : array) {
            if (item == value) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int get(int index) {
        // TODO: получить элемент по индексу
        checkIndex(index);
        return array[index];
    }

    @Override
    public int set(int index, int value) {
        // TODO: присвоить значение по индексу. Вернуть значение элемента, которое заменили
        checkIndex(index);
        int oldValue = array[index];
        array[index] = value;
        return oldValue;
    }

    @Override
    public int indexOf(int value) {
        // TODO: получить индекс первого подходящего элемента
        for (int i = 0; i < array.length; i++) {
            if (array[i] == value) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(int value) {
        // TODO: получить индекс последнего подходящего элемента
        for (int i = array.length - 1; i >= 0; i--) {
            if (array[i] == value) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void reverse() {
        // TODO: перевернуть массив
        for (int i = 0; i < array.length / 2; i++) {
            int temp = array[i];
            array[i] = array[array.length - 1 - i];
            array[array.length - 1 - i] = temp;
        }
    }

    @Override
    public Array subArray(int fromIndex, int toIndex) {
        // TODO: вернуть подмассив, начиная с индекса fromIndex включительно и заканчивая индексом toIndex невкоючительно
        checkIndex(fromIndex);
        checkIndex(toIndex);
        if (toIndex <= fromIndex) {
            throw new IllegalArgumentException("Pizdec");
        }
        int[] subArray = new int[toIndex - fromIndex];
        if (toIndex - fromIndex >= 0) {
            System.arraycopy(array, fromIndex, subArray, fromIndex, toIndex - fromIndex);
        }
        return new StaticArray(subArray);
    }

    @Override
    public void sort() {
        bubbleSort();
    }

    @Override
    public void sort(ArraySort sort) {
        switch (sort) {
            case BUBBLE:
                bubbleSort();
                break;
            case INSERTION:
                insertionSort();
                break;
            case SELECTION:
                selectionSort();
                break;
            case MERGE:
                mergeSort();
                break;
            case QUICK:
                quickSort();
                break;
            default:
                sort();
        }
    }

    @Override
    public String toString() {
        // TODO: вернуть массив в виде строки. Например, [3, 4, 6, -2]
        int maxIndex = array.length - 1;
        if (maxIndex == -1) {
            return "[]";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < array.length; i++) {
            sb.append(array[i]);
            if (i == maxIndex) {
                sb.append("]");
                return sb.toString();
            }
            sb.append(", ");
        }
        return "null";
    }

    private void bubbleSort() {
        // TODO: сортировка пузырьком (по возрастанию)
        for (int i = 0; i < array.length; i++) {
            if (this.isAscSorted()) {
                return;
            }
            for (int j = 0; j < array.length - 1; j++) {
                if (array[j] > array[j+1]) {
                    int temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                }
            }
        }
    }

    private void insertionSort() {
        //TODO*: сортировка вставками (по возрастанию)
        for (int i = 1; i < array.length; i++) {
            int current = array[i];
            int j = i - 1;
            while (j >= 0 && current < array[j]) {
                array[j+1] = array[j];
                j--;
            }
            array[j+1] = current;
        }
    }

    private void selectionSort() {
        //TODO*: сортировка выбором (по возрастанию)
        for (int i = 0; i < array.length; i++) {
            int minValue = array[i];
            int minIndex = i;
            for (int j = i+1; j < array.length; j++) {
                if (array[j] < minValue) {
                    minValue = array[j];
                    minIndex = j;
                }
            }
            int temp = array[i];
            array[i] = minValue;
            array[minIndex] = temp;
        }
    }

    private void mergeSort() {
        //TODO**: сортировка слиянием (по возрастанию)
        mergeSortRecursion(0, array.length-1);
    }

    private void mergeSortRecursion(int left, int right) {
        if (right <= left) {
            return;
        }
        int middle = (left + right) / 2;
        mergeSortRecursion(left, middle);
        mergeSortRecursion(middle+1, right);
        merge(left, middle, right);
    }

    private void merge(int left, int middle, int right) {
        // собсна слияние
        int lengthLeft = middle - left + 1;
        int lengthRight = right - middle;

        int[] leftArray = new int[lengthLeft];
        int[] rightArray = new int[lengthRight];

        for (int i = 0; i < lengthLeft; i++) {
            leftArray[i] = array[left+i];
        }
        for (int i = 0; i < lengthRight; i++) {
            rightArray[i] = array[middle+i+1];
        }

        int leftIndex = 0;
        int rightIndex = 0;

        for (int i = left; i < right + 1; i++) {
            if (leftIndex < lengthLeft && rightIndex < lengthRight) {
                if (leftArray[leftIndex] < rightArray[rightIndex]) {
                    array[i] = leftArray[leftIndex];
                    leftIndex++;
                } else {
                    array[i] = rightArray[rightIndex];
                    rightIndex++;
                }
            } else if (leftIndex < lengthLeft) {
                array[i] = leftArray[leftIndex];
                leftIndex++;
            } else if (rightIndex < lengthRight) {
                array[i] = rightArray[rightIndex];
                rightIndex++;
            }
        }
    }

    private void quickSort() {
        //TODO**: быстрая сортировка (по возрастанию)
        quickSortRecursion(0, array.length-1);
    }

    private void quickSortRecursion(int begin, int end) {
        if (end <= begin) {
            return;
        }
        int pivot = partition(begin, end);
        quickSortRecursion(begin, pivot-1);
        quickSortRecursion(pivot+1, end);
    }

    private int partition(int begin, int end) {
        int pivot = end;

        int counter = begin;
        for (int i = begin; i < end; i++) {
            if (array[i] < array[pivot]) {
                int temp = array[counter];
                array[counter] = array[i];
                array[i] = temp;
                counter++;
            }
        }
        int temp = array[pivot];
        array[pivot] = array[counter];
        array[counter] = temp;

        return counter;
    }

    public boolean isAscSorted() {
        // TODO: проверить, что массив отсортирован по возрастанию
        for (int i = 1; i < array.length; i++) {
            if (array[i - 1] > array[i]) {
                return false;
            }
        }
        return true;
    }

    protected void checkIndex(int index) {
        if (index < 0 || index >= array.length) {
            throw new ArrayIndexOutOfBoundsException("Pizdec");
        }
    }
}
