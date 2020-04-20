package com.gmail.docfordja;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by anton on 14.04.2020.
 */
public class RoomMessageList {

    private List<Message> list = new List<Message>() {
        @Override
        public int size() {
            for (int i = 0; ; i++) {
                if (list.get(i) == null) {
                    return i;
                }
            }
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public boolean contains(Object o) {
            return false;
        }

        @Override
        public Iterator<Message> iterator() {
            return null;
        }

        @Override
        public Object[] toArray() {
            return new Object[0];
        }

        @Override
        public <T> T[] toArray(T[] a) {
            return null;
        }

        @Override
        public boolean add(Message message) {
            list.add(message);
            return false;
        }

        @Override
        public boolean remove(Object o) {
            return false;
        }

        @Override
        public boolean containsAll(Collection<?> c) {
            return false;
        }

        @Override
        public boolean addAll(Collection<? extends Message> c) {
            return false;
        }

        @Override
        public boolean addAll(int index, Collection<? extends Message> c) {
            return false;
        }

        @Override
        public boolean removeAll(Collection<?> c) {
            return false;
        }

        @Override
        public boolean retainAll(Collection<?> c) {
            return false;
        }

        @Override
        public void clear() {

        }

        @Override
        public Message get(int index) {
            return list.get(index);
        }

        @Override
        public Message set(int index, Message element) {
            return null;
        }

        @Override
        public void add(int index, Message element) {

        }

        @Override
        public Message remove(int index) {
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
        public ListIterator<Message> listIterator() {
            return null;
        }

        @Override
        public ListIterator<Message> listIterator(int index) {
            return null;
        }

        @Override
        public List<Message> subList(int fromIndex, int toIndex) {
            return null;
        }
    };

    public void add(Message m) {
        list.add(m);
    }
}
