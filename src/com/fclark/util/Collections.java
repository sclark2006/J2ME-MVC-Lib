package com.fclark.util;

import java.util.Vector;

public class Collections {

    public static final int ORDER_ASCENDING = 1;
    public static final int ORDER_DESCENDING = -1;
    public static final Vector EMPTY_VECTOR = new Vector(1);
    public static final List EMPTY_LIST = new List(1);
    
    private static Vector merge(Vector izquierda, Vector derecha, Comparator comparator) {
        Vector result = new Vector();
        while (izquierda.size() > 0 || derecha.size() > 0) {
            if (izquierda.size() > 0 && derecha.size() > 0) {
                int cmp;
                if (comparator == null) {
                    cmp = ((Comparable) izquierda.elementAt(0)).compareTo(derecha.elementAt(0));
                } else {
                    cmp = comparator.compare(izquierda.elementAt(0), derecha.elementAt(0));
                }
                if (cmp <= 0) {
                    result.addElement(izquierda.elementAt(0));
                    izquierda.removeElementAt(0);
                } else {
                    result.addElement(derecha.elementAt(0));
                    derecha.removeElementAt(0);
                }
            } else if (izquierda.size() > 0) {
                result.addElement(izquierda.elementAt(0));
                izquierda.removeElementAt(0);
            } else if (derecha.size() > 0) {
                result.addElement(derecha.elementAt(0));
                derecha.removeElementAt(0);
            }
        }//while
        return result;
    }

    public static Vector mergeSort(Vector vector, Comparator cmp) {
        if (vector.size() <= 1) {
            return vector;
        }
        Vector izquierda, derecha, result;
        int centro = (int) Math.floor(vector.size() / 2);
        izquierda = new Vector();
        for (int i = 0; i < centro; i++) {
            Object obj = vector.elementAt(i);
            izquierda.addElement(obj);
        }
        derecha = new Vector();
        for (int i = centro; i < vector.size(); i++) {
            Object obj = vector.elementAt(i);
            derecha.addElement(obj);
        }
        izquierda = mergeSort(izquierda, cmp);
        derecha = mergeSort(derecha, cmp);
        result = merge(izquierda, derecha, cmp);
        izquierda = null;
        derecha = null;
        return result;
    }

    public static Vector mergeSort(Vector vector) {
        return mergeSort(vector, null);
    }

    public static Vector sort(Vector entrada) {
        return mergeSort(entrada, null);
    }   

    public static Vector sort(Vector entrada, Comparator cmp) {
        return mergeSort(entrada, cmp);
    }

    public static Vector filter(Vector entrada, Object obj, Comparator cmp) {
        Vector result = new Vector();
        for (int i = 0; i < entrada.size(); i++) {
            if (cmp.compare(entrada.elementAt(i), obj) == 0) {
                result.addElement(entrada.elementAt(i));
            }
        }
        return result;
    }
    
    public static int search(Vector source, Comparable searchFor) {
        for (int i = 0; i < source.size(); i++) 
            if(searchFor.equals(source.elementAt(i)))
                return i;            
        return -1;
    }

    public static Object search(Vector entrada, Object obj, Comparator cmp) {
        Object result = null;
        for (int i = 0; i < entrada.size(); i++) {
            if (cmp.compare(entrada.elementAt(i), obj) == 0) {
                result = entrada.elementAt(i);
                break;
            }
        }
        return result;
    }

    public static int indexOf(Vector entrada, Object obj, Comparator cmp) {
        int result = -1;
        for (int i = 0; i < entrada.size(); i++) {
            if (cmp.compare(entrada.elementAt(i), obj) == 0) {
                result = i;
                break;
            }
        }
        return result;
    }

    public static Vector emptyVector() {
        return EMPTY_VECTOR;
    }
    
    public static List emptyList() {
        return EMPTY_LIST;
    }
    
    public static String toString(Vector vector) {
        return vector.toString();
    }
    
    public static String toString(Object[] array) {
        if(array == null)
            return null;
        
        StringBuffer sb = new StringBuffer("{");
        for(int i=0; i < array.length; i++) {
            if(i > 0)
                sb.append(',');
            sb.append(array[i]);
        }
        sb.append("}");
        return sb.toString();
    }
}
