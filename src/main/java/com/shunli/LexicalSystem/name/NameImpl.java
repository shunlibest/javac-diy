package com.shunli.LexicalSystem.name;

public class NameImpl extends Name {

    //下一个Name所在的bucket
    NameImpl next;

    //Name字节在全局的index
    int index;

    //此Name的length(字节)
    int length;

    public NameImpl(SharedNameTable table) {
        super(table);
    }

    @Override
    public int getIndex() {
        return index;
    }

    @Override
    public int getByteLength() {
        return length;
    }

    @Override
    public byte getByteAt(int i) {
        return getByteArray()[index + i];
    }

    @Override
    public byte[] getByteArray() {
        return ((SharedNameTable) table).bytes;
    }

    @Override
    public int getByteOffset() {
        return index;
    }

    @Override
    public int hashCode() {
        return index;
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Name) {
            return table == ((Name) other).table && index == ((Name) other).getIndex();
        } else {
            return false;
        }
    }

}
