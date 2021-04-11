package course.task;

/**
 * Сортированный статический массив (по возрастанию).
 *
 * Любая операция должна гарантировать, что массив, по ее окончании, отстортирован
 */
public class SortedArray extends StaticArray {

    public SortedArray(int[] array) {
        super(array);
        if (!isAscSorted()) {
            sort();
        }
    }

    @Override
    public boolean contains(int value) {
        return binarySearch(value, 0, array.length) != -1;
    }

    @Override
    public int set(int index, int value) {
        // TODO: присовить значение по индексу
        int oldValue = super.set(index, value);
        if (!isAscSorted()) {
            sort();
        }
        return oldValue;
    }

    @Override
    public int indexOf(int value) {
        return binarySearch(value, 0, array.length - 1);
    }

    @Override
    public int lastIndexOf(int value) {
        // TODO: получить индекс последнего подходящего элемента
        return super.lastIndexOf(value);
    }

    @Override
    public void sort() {
        super.sort();
    }

    @Override
    public void sort(ArraySort sort) {
        super.sort(sort);
    }

    private int binarySearch(int value, int left, int right) {
        // TODO: реализовать бинарный
        if (!isAscSorted()) {
            sort();
        }
        while (left <= right) {
            int middle = (left + right) / 2;
            if (array[middle] == value) {
                return middle;
            } else if (array[middle] < value) {
                left = middle + 1;
            } else if (array[middle] > value) {
                right = middle - 1;
            }
        }
        return -1;
    }

    public SortedArray merge(SortedArray other) {
        // TODO: произвести слиянеие двух сортированных массивов
        int[] newArr = new int[this.array.length + other.array.length];
        int leftIndex = 0;
        int rightIndex = 0;
        for (int i = 0; i < newArr.length; i++) {
            if (leftIndex < this.array.length && rightIndex < other.array.length) {
                if (this.array[leftIndex] < other.array[rightIndex]) {
                    newArr[i] = this.array[leftIndex];
                    leftIndex++;
                } else {
                    newArr[i] = other.array[rightIndex];
                    rightIndex++;
                }
            } else if (leftIndex < this.array.length) {
                newArr[i] = this.array[leftIndex];
                leftIndex++;
            } else if (rightIndex < other.array.length) {
                newArr[i] = other.array[rightIndex];
                rightIndex++;
            }
        }
        return new SortedArray(newArr);
    }

    public SortedArray mergeAll(SortedArray... others) {
        // TODO: произвести слиянеие N + 1 сортированных массивов
        SortedArray newSA = this;
        for (int i = 0; i < others.length; i++) {
            newSA = newSA .merge(others[i]);
        }
        return newSA;
    }
}
