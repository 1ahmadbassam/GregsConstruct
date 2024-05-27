package gregsconstruct.lib;

import javax.annotation.Nonnull;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ignoredList<T> implements List<T> {

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return true;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    @Nonnull
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public T next() {
                return null;
            }
        };
    }

    @Override
    @Nonnull
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    @Nonnull
    public <T1> T1[] toArray(@Nonnull T1[] a) {
        return a;
    }

    @Override
    public boolean add(T t) {
        return true;
    }

    @Override
    public boolean remove(Object o) {
        return true;
    }

    @Override
    public boolean containsAll(@Nonnull Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(@Nonnull Collection<? extends T> c) {
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        return true;
    }

    @Override
    public boolean removeAll(@Nonnull Collection<?> c) {
        return true;
    }

    @Override
    public boolean retainAll(@Nonnull Collection<?> c) {
        return true;
    }

    @Override
    public void clear() {
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof ignoredList;
    }

    @Override
    public int hashCode() {
        return 671;
    }

    @Override
    public T get(int index) {
        return null;
    }

    @Override
    public T set(int index, T element) {
        return null;
    }

    @Override
    public void add(int index, T element) {
    }

    @Override
    public T remove(int index) {
        return null;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    @Nonnull
    public ListIterator<T> listIterator() {
        return gettListIterator();
    }

    @Override
    @Nonnull
    public ListIterator<T> listIterator(int index) {
        return gettListIterator();
    }

    private ListIterator<T> gettListIterator() {
        return new ListIterator<T>() {
            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public T next() {
                return null;
            }

            @Override
            public boolean hasPrevious() {
                return false;
            }

            @Override
            public T previous() {
                return null;
            }

            @Override
            public int nextIndex() {
                return 0;
            }

            @Override
            public int previousIndex() {
                return 0;
            }

            @Override
            public void remove() {
            }

            @Override
            public void set(T t) {
            }

            @Override
            public void add(T t) {
            }
        };
    }

    @Override
    @Nonnull
    public List<T> subList(int fromIndex, int toIndex) {
        return this;
    }
}
