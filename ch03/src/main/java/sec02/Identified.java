package sec02;

public interface Identified {
    default int getId() { return Math.abs(hashCode()); } 
}