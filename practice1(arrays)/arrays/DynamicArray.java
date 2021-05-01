package course.task;

import java.util.Arrays;

/**
 * Динамический массив
 */
public class DynamicArray extends StaticArray implements Dynamic {

    private static final int DEFAULT_CAPACITY = 10;
    private static final float GROW_FACTOR = 1.5f;

    /**
     * текущая длина массива
     */
    private int size;

    private int capacityDA;

    public DynamicArray() {
        super(new int[DEFAULT_CAPACITY]);
        size = 0;
        capacityDA = DEFAULT_CAPACITY;
    }

    public DynamicArray(int[] array) {
        super(array);
        size = array.length;
        capacityDA = (int) (array.length * GROW_FACTOR + 1);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void add(int value) {
        // TODO: добавить элемент в конец массива
        if (size == capacityDA) {
            grow();
        }
        super.array[size] = value;
        size++;
    }

    private void grow() {
        capacityDA = (int) (capacityDA * GROW_FACTOR + 1);
        array = Arrays.copyOf(array, capacityDA);
    }

    @Override
    public void add(int index, int value) {
        // TODO: добавить элемент в указанный индекс (остальные сдвинуть вправо)
        checkIndex(index);
        if (index == size) {
            add(value);
        }
        if (size == capacityDA) {
            grow();
        }
        System.arraycopy(array, index, array, index+1, size-index);
        array[index] = value;
        size++;
    }



    @Override
    public void addAll(Array array) {
        // TODO: конкатенация с переданным массивом
        for (int i = 0; i < array.size(); i++) {
            add(array.get(i));
        }
        size = size + array.size();
    }

    @Override
    public boolean remove(int value) {
        // TODO: удалить элемент из массива
        boolean flag = false;
        for (int i = 0; i < size; i++) {
            if (array[i] == value) {
                flag = true;
                removeOf(i);
                break;
            }
        }
        return flag;
    }

    @Override
    public boolean removeAll(int[] values) {
        // TODO: удалить все указанные элементы из массива
        boolean flag = false;
        for (int i = 0; i < values.length; i++) {
            flag = remove(values[i]);
        }
        return flag;
    }

    @Override
    public int removeOf(int index) {
        // TODO: удалить элемент по индексу
        checkIndex(index);
        int oldValue = array[index];
        System.arraycopy(array, index+1, array, index, size-index-1);
        array[size-1] = 0;
        size--;
        return oldValue;
    }
}
