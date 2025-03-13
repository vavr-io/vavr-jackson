package io.vavr.jackson.datatype.bean;

import io.vavr.Tuple;
import io.vavr.Tuple2;
import io.vavr.collection.Array;
import io.vavr.collection.HashMap;
import io.vavr.collection.HashMultimap;
import io.vavr.collection.HashSet;
import io.vavr.collection.List;
import io.vavr.collection.Queue;
import io.vavr.collection.Stream;
import io.vavr.collection.TreeMap;
import io.vavr.collection.TreeMultimap;
import io.vavr.collection.TreeSet;
import io.vavr.collection.Vector;
import io.vavr.control.Option;

import java.io.Serializable;

public class ComplexClass implements Serializable {

    private static final long serialVersionUID = 6119682094978738461L;

    private HashMap<String, ComplexInnerClass> complexInnerClassHashMap;
    private TreeMap<Integer, ComplexInnerClass> complexInnerClassTreeMap;
    private HashMultimap<String, ComplexInnerClass> complexInnerClassHashMultimap;
    private TreeMultimap<Integer, ComplexInnerClass> complexInnerClassTreeMultimap;
    private Array<ComplexInnerClass> complexInnerClasses;
    private List<ComplexInnerClass> complexInnerClassList;
    private Queue<ComplexInnerClass> complexInnerClassQueue;
    private Stream<ComplexInnerClass> complexInnerClassStream;
    private Vector<ComplexInnerClass> complexInnerClassVector;
    private HashSet<ComplexInnerClass> complexInnerClassHashSet;
    private TreeSet<ComplexInnerClass> complexInnerClassTreeSet;
    private Tuple2<String, ComplexInnerClass> complexInnerClassTuple2;
    private Option<Integer> opt1;
    private Option<Integer> opt2;

    public ComplexClass() {
    }

    public ComplexClass(final HashMap<String, ComplexInnerClass> complexInnerClassHashMap,
                        final TreeMap<Integer, ComplexInnerClass> complexInnerClassTreeMap,
                        final HashMultimap<String, ComplexInnerClass> complexInnerClassHashMultimap,
                        final TreeMultimap<Integer, ComplexInnerClass> complexInnerClassTreeMultimap,
                        final Array<ComplexInnerClass> complexInnerClasses,
                        final List<ComplexInnerClass> complexInnerClassList,
                        final Queue<ComplexInnerClass> complexInnerClassQueue,
                        final Stream<ComplexInnerClass> complexInnerClassStream,
                        final Vector<ComplexInnerClass> complexInnerClassVector,
                        final HashSet<ComplexInnerClass> complexInnerClassHashSet,
                        final TreeSet<ComplexInnerClass> complexInnerClassTreeSet,
                        final Tuple2<String, ComplexInnerClass> complexInnerClassTuple2,
                        final Option<Integer> opt1,
                        final Option<Integer> opt2) {
        this.complexInnerClassHashMap = complexInnerClassHashMap;
        this.complexInnerClassTreeMap = complexInnerClassTreeMap;
        this.complexInnerClassHashMultimap = complexInnerClassHashMultimap;
        this.complexInnerClassTreeMultimap = complexInnerClassTreeMultimap;
        this.complexInnerClasses = complexInnerClasses;
        this.complexInnerClassList = complexInnerClassList;
        this.complexInnerClassQueue = complexInnerClassQueue;
        this.complexInnerClassStream = complexInnerClassStream;
        this.complexInnerClassVector = complexInnerClassVector;
        this.complexInnerClassHashSet = complexInnerClassHashSet;
        this.complexInnerClassTreeSet = complexInnerClassTreeSet;
        this.complexInnerClassTuple2 = complexInnerClassTuple2;
        this.opt1 = opt1;
        this.opt2 = opt2;
    }

    @SuppressWarnings("unchecked")
    public static ComplexClass build() {
        return new ComplexClass(
            HashMap.of(Tuple.of("42", ComplexInnerClass.build())),
            TreeMap.of(Tuple.of(42, ComplexInnerClass.buildAnother())),
            HashMultimap.withSeq().of(Tuple.of("42", ComplexInnerClass.build())),
            TreeMultimap.withSeq().of(Tuple.of(42, ComplexInnerClass.buildAnother())),
            Array.of(ComplexInnerClass.build(), ComplexInnerClass.buildAnother()),
            List.of(ComplexInnerClass.build(), ComplexInnerClass.buildAnother()),
            Queue.of(ComplexInnerClass.build(), ComplexInnerClass.buildAnother()),
            Stream.of(ComplexInnerClass.build(), ComplexInnerClass.buildAnother()),
            Vector.of(ComplexInnerClass.build(), ComplexInnerClass.buildAnother()),
            HashSet.of(ComplexInnerClass.build(), ComplexInnerClass.buildAnother()),
            TreeSet.of(ComplexInnerClass.build(), ComplexInnerClass.buildAnother()),
            Tuple.of("42", ComplexInnerClass.build()),
            Option.of(42), Option.none()
        );
    }

    public HashMultimap<String, ComplexInnerClass> getComplexInnerClassHashMultimap() {
        return complexInnerClassHashMultimap;
    }

    public void setComplexInnerClassHashMultimap(final HashMultimap<String, ComplexInnerClass> complexInnerClassHashMultimap) {
        this.complexInnerClassHashMultimap = complexInnerClassHashMultimap;
    }

    public TreeMultimap<Integer, ComplexInnerClass> getComplexInnerClassTreeMultimap() {
        return complexInnerClassTreeMultimap;
    }

    public void setComplexInnerClassTreeMultimap(final TreeMultimap<Integer, ComplexInnerClass> complexInnerClassHashMultimap) {
        this.complexInnerClassTreeMultimap = complexInnerClassHashMultimap;
    }

    public HashMap<String, ComplexInnerClass> getComplexInnerClassHashMap() {
        return complexInnerClassHashMap;
    }

    public void setComplexInnerClassHashMap(final HashMap<String, ComplexInnerClass> complexInnerClassHashMap) {
        this.complexInnerClassHashMap = complexInnerClassHashMap;
    }

    public TreeMap<Integer, ComplexInnerClass> getComplexInnerClassTreeMap() {
        return complexInnerClassTreeMap;
    }

    public void setComplexInnerClassTreeMap(final TreeMap<Integer, ComplexInnerClass> complexInnerClassTreeMap) {
        this.complexInnerClassTreeMap = complexInnerClassTreeMap;
    }

    public Array<ComplexInnerClass> getComplexInnerClasses() {
        return complexInnerClasses;
    }

    public void setComplexInnerClasses(final Array<ComplexInnerClass> complexInnerClasses) {
        this.complexInnerClasses = complexInnerClasses;
    }

    public List<ComplexInnerClass> getComplexInnerClassList() {
        return complexInnerClassList;
    }

    public void setComplexInnerClassList(final List<ComplexInnerClass> complexInnerClassList) {
        this.complexInnerClassList = complexInnerClassList;
    }

    public Queue<ComplexInnerClass> getComplexInnerClassQueue() {
        return complexInnerClassQueue;
    }

    public void setComplexInnerClassQueue(final Queue<ComplexInnerClass> complexInnerClassQueue) {
        this.complexInnerClassQueue = complexInnerClassQueue;
    }

    public Stream<ComplexInnerClass> getComplexInnerClassStream() {
        return complexInnerClassStream;
    }

    public void setComplexInnerClassStream(final Stream<ComplexInnerClass> complexInnerClassStream) {
        this.complexInnerClassStream = complexInnerClassStream;
    }

    public Vector<ComplexInnerClass> getComplexInnerClassVector() {
        return complexInnerClassVector;
    }

    public void setComplexInnerClassVector(final Vector<ComplexInnerClass> complexInnerClassVector) {
        this.complexInnerClassVector = complexInnerClassVector;
    }

    public HashSet<ComplexInnerClass> getComplexInnerClassHashSet() {
        return complexInnerClassHashSet;
    }

    public void setComplexInnerClassHashSet(final HashSet<ComplexInnerClass> complexInnerClassHashSet) {
        this.complexInnerClassHashSet = complexInnerClassHashSet;
    }

    public TreeSet<ComplexInnerClass> getComplexInnerClassTreeSet() {
        return complexInnerClassTreeSet;
    }

    public void setComplexInnerClassTreeSet(final TreeSet<ComplexInnerClass> complexInnerClassTreeSet) {
        this.complexInnerClassTreeSet = complexInnerClassTreeSet;
    }

    public Tuple2<String, ComplexInnerClass> getComplexInnerClassTuple2() {
        return complexInnerClassTuple2;
    }

    public void setComplexInnerClassTuple2(final Tuple2<String, ComplexInnerClass> complexInnerClassTuple2) {
        this.complexInnerClassTuple2 = complexInnerClassTuple2;
    }

    public Option<Integer> getOpt1() {
        return opt1;
    }

    public void setOpt1(Option<Integer> opt1) {
        this.opt1 = opt1;
    }

    public Option<Integer> getOpt2() {
        return opt2;
    }

    public void setOpt2(Option<Integer> opt2) {
        this.opt2 = opt2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ComplexClass that = (ComplexClass) o;

        if (complexInnerClassHashMap != null ? !complexInnerClassHashMap.equals(that.complexInnerClassHashMap) : that.complexInnerClassHashMap != null)
            return false;
        if (complexInnerClassTreeMap != null ? !complexInnerClassTreeMap.equals(that.complexInnerClassTreeMap) : that.complexInnerClassTreeMap != null)
            return false;
        if (complexInnerClassHashMultimap != null ? !complexInnerClassHashMultimap.equals(that.complexInnerClassHashMultimap) : that.complexInnerClassHashMultimap != null)
            return false;
        if (complexInnerClassTreeMultimap != null ? !complexInnerClassTreeMultimap.equals(that.complexInnerClassTreeMultimap) : that.complexInnerClassTreeMultimap != null)
            return false;
        if (complexInnerClasses != null ? !complexInnerClasses.equals(that.complexInnerClasses) : that.complexInnerClasses != null)
            return false;
        if (complexInnerClassList != null ? !complexInnerClassList.equals(that.complexInnerClassList) : that.complexInnerClassList != null)
            return false;
        if (complexInnerClassQueue != null ? !complexInnerClassQueue.equals(that.complexInnerClassQueue) : that.complexInnerClassQueue != null)
            return false;
        if (complexInnerClassStream != null ? !complexInnerClassStream.equals(that.complexInnerClassStream) : that.complexInnerClassStream != null)
            return false;
        if (complexInnerClassVector != null ? !complexInnerClassVector.equals(that.complexInnerClassVector) : that.complexInnerClassVector != null)
            return false;
        if (complexInnerClassHashSet != null ? !complexInnerClassHashSet.equals(that.complexInnerClassHashSet) : that.complexInnerClassHashSet != null)
            return false;
        if (complexInnerClassTreeSet != null ? !complexInnerClassTreeSet.equals(that.complexInnerClassTreeSet) : that.complexInnerClassTreeSet != null)
            return false;
        if (complexInnerClassTuple2 != null ? !complexInnerClassTuple2.equals(that.complexInnerClassTuple2) : that.complexInnerClassTuple2 != null)
            return false;
        if (opt1 != null ? !opt1.equals(that.opt1) : that.opt1 != null) return false;
        return !(opt2 != null ? !opt2.equals(that.opt2) : that.opt2 != null);
    }

    @Override
    public int hashCode() {
        int result = complexInnerClassHashMap != null ? complexInnerClassHashMap.hashCode() : 0;
        result = 31 * result + (complexInnerClassTreeMap != null ? complexInnerClassTreeMap.hashCode() : 0);
        result = 31 * result + (complexInnerClassHashMultimap != null ? complexInnerClassHashMultimap.hashCode() : 0);
        result = 31 * result + (complexInnerClassTreeMultimap != null ? complexInnerClassTreeMultimap.hashCode() : 0);
        result = 31 * result + (complexInnerClasses != null ? complexInnerClasses.hashCode() : 0);
        result = 31 * result + (complexInnerClassList != null ? complexInnerClassList.hashCode() : 0);
        result = 31 * result + (complexInnerClassQueue != null ? complexInnerClassQueue.hashCode() : 0);
        result = 31 * result + (complexInnerClassStream != null ? complexInnerClassStream.hashCode() : 0);
        result = 31 * result + (complexInnerClassVector != null ? complexInnerClassVector.hashCode() : 0);
        result = 31 * result + (complexInnerClassHashSet != null ? complexInnerClassHashSet.hashCode() : 0);
        result = 31 * result + (complexInnerClassTreeSet != null ? complexInnerClassTreeSet.hashCode() : 0);
        result = 31 * result + (complexInnerClassTuple2 != null ? complexInnerClassTuple2.hashCode() : 0);
        result = 31 * result + (opt1 != null ? opt1.hashCode() : 0);
        result = 31 * result + (opt2 != null ? opt2.hashCode() : 0);
        return result;
    }

    public static class ComplexInnerClass implements Comparable<ComplexInnerClass>, Serializable {

        private static final long serialVersionUID = 9052404750897385370L;

        private int intField;
        private long longField;
        private double doubleField;
        private float floatField;
        private char charField;
        private byte byteField;
        private short shortField;
        private boolean booleanField;
        private String stringField;

        public ComplexInnerClass() {
        }

        public ComplexInnerClass(final int intField,
                                 final long longField,
                                 final double doubleField,
                                 final float floatField,
                                 final char charField,
                                 final byte byteField,
                                 final short shortField,
                                 final boolean booleanField,
                                 final String stringField) {
            this.intField = intField;
            this.longField = longField;
            this.doubleField = doubleField;
            this.floatField = floatField;
            this.charField = charField;
            this.byteField = byteField;
            this.shortField = shortField;
            this.booleanField = booleanField;
            this.stringField = stringField;
        }

        public static ComplexInnerClass build() {
            return new ComplexInnerClass(42,
                42L,
                42.42d,
                42.42f,
                '4',
                Integer.valueOf(42).byteValue(),
                Integer.valueOf(42).shortValue(),
                false,
                "42");
        }

        public static ComplexInnerClass buildAnother() {
            return new ComplexInnerClass(87,
                87L,
                87.87d,
                87.87f,
                '8',
                Integer.valueOf(87).byteValue(),
                Integer.valueOf(87).shortValue(),
                false,
                "87");
        }

        public int getIntField() {
            return intField;
        }

        public void setIntField(final int intField) {
            this.intField = intField;
        }

        public long getLongField() {
            return longField;
        }

        public void setLongField(final long longField) {
            this.longField = longField;
        }

        public double getDoubleField() {
            return doubleField;
        }

        public void setDoubleField(final double doubleField) {
            this.doubleField = doubleField;
        }

        public float getFloatField() {
            return floatField;
        }

        public void setFloatField(final float floatField) {
            this.floatField = floatField;
        }

        public char getCharField() {
            return charField;
        }

        public void setCharField(final char charField) {
            this.charField = charField;
        }

        public byte getByteField() {
            return byteField;
        }

        public void setByteField(final byte byteField) {
            this.byteField = byteField;
        }

        public short getShortField() {
            return shortField;
        }

        public void setShortField(final short shortField) {
            this.shortField = shortField;
        }

        public boolean isBooleanField() {
            return booleanField;
        }

        public void setBooleanField(final boolean booleanField) {
            this.booleanField = booleanField;
        }

        public String getStringField() {
            return stringField;
        }

        public void setStringField(final String stringField) {
            this.stringField = stringField;
        }

        /**
         * Only to Satisfy Comparable requirement
         *
         * @param o Object to Compare
         *
         * @return Always return 0
         */
        @Override
        public int compareTo(ComplexInnerClass o) {
            return 0;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            ComplexInnerClass that = (ComplexInnerClass) o;

            if (intField != that.intField) return false;
            if (longField != that.longField) return false;
            if (Double.compare(that.doubleField, doubleField) != 0) return false;
            if (Float.compare(that.floatField, floatField) != 0) return false;
            if (charField != that.charField) return false;
            if (byteField != that.byteField) return false;
            if (shortField != that.shortField) return false;
            if (booleanField != that.booleanField) return false;
            return !(stringField != null ? !stringField.equals(that.stringField) : that.stringField != null);
        }

        @Override
        public int hashCode() {
            int result;
            final long temp;
            result = intField;
            result = 31 * result + (int) (longField ^ (longField >>> 32));
            temp = Double.doubleToLongBits(doubleField);
            result = 31 * result + (int) (temp ^ (temp >>> 32));
            result = 31 * result + (floatField != +0.0f ? Float.floatToIntBits(floatField) : 0);
            result = 31 * result + (int) charField;
            result = 31 * result + (int) byteField;
            result = 31 * result + (int) shortField;
            result = 31 * result + (booleanField ? 1 : 0);
            result = 31 * result + (stringField != null ? stringField.hashCode() : 0);
            return result;
        }
    }
}
