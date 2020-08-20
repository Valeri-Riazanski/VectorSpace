package com.val.idea;

public class VectorSpaceTest {
  public static void main(String[] args) {
    VectorSpace vectorA = new VectorSpace(0.000000001, 0.000000001, 0.00000001);
    VectorSpace vectorB = new VectorSpace(2, 2, 2);
    System.out.print("vector A = ");
    vectorA.printCoordinateVector();
    System.out.print("vector b = ");
    vectorB.printCoordinateVector();
    VectorSpace vectorC = vectorA.getSumVectors(vectorB);
    System.out.print("vector A + B = ");
    vectorC.printCoordinateVector();
    VectorSpace vectorD = vectorB.getSubVectors(vectorA);
    System.out.print("vector B - A = ");
    vectorD.printCoordinateVector();
    VectorSpace vectorE = vectorA.getMultiVectorByNumber(3.0);
    System.out.print("vector 3 * A = ");
    vectorE.printCoordinateVector();
    System.out.println("A * B = " + vectorA.getMultiScalarVectors(vectorB));
    VectorSpace vectorF = vectorA.getMultiVectors(vectorB);
    System.out.print("A x B = ");
    vectorF.printCoordinateVector();
    System.out.println("|B| = " + vectorB.getNormaVector());
    VectorSpace vectorG = VectorSpace.getRandomVector(1,9);
    System.out.print("random vector = " );
    vectorG.printCoordinateVector();
    int n = 3;
    System.out.println("Arrays random vectors");
    for (VectorSpace v : VectorSpace.getArrayRandomVectors(1, 9, n)){
        v.printCoordinateVector();
    }
  }
}

class VectorSpace{
  private final double x1;
  private final double x2;
  private final double x3;
  public VectorSpace(double y1, double y2, double y3){
      this.x1 = y1;
      this.x2 = y2;
      this.x3 = y3;
  }
  public double getX1(){
      return this.x1;
  }

  public double getX2(){
      return this.x2;
  }

  public double getX3(){
      return this.x3;
  }
  public void printCoordinateVector(){
      System.out.println("{ " + this.x1 + ", " + this.x2 + ", " + this.x3 + " }");
  }
  public VectorSpace getSumVectors(VectorSpace a){
    double z1 = this.x1 + a.getX1();
    double z2 = this.x2 + a.getX2();
    double z3 = this.x3 + a.getX3();
    return new VectorSpace(z1,z2,z3);
  }
  public VectorSpace getSubVectors(VectorSpace a){
    double t1 = this.x1 - a.getX1();
    double t2 = this.x2 - a.getX2();
    double t3 = this.x3 - a.getX3();
    return new VectorSpace(t1, t2, t3);
  }
  public VectorSpace getMultiVectorByNumber(double p){
    double u1 = this.x1 * p;
    double u2 = this.x2 * p;
    double u3 = this.x3 * p;
    return new VectorSpace(u1, u2, u3);
  }
  public double getMultiScalarVectors(VectorSpace a){
      return this.x1 * a.getX1() + this.x2 * a.getX2() + this.x3 * a.getX3();
  }
  public VectorSpace getMultiVectors(VectorSpace b){
      double epsilon = 0.1E-06;
      if( Math.abs(this.x1) + Math.abs(this.x2) + Math.abs(this.x3) < epsilon) {
          return  new VectorSpace(0.0, 0.0, 0.0);
      }
      if( Math.abs(b.getX1()) + Math.abs(b.getX2()) + Math.abs(b.getX3()) < epsilon) {
          return  new VectorSpace(0.0, 0.0, 0.0);
      }
      if ((Math.abs(this.x1 / b.getX1() - this.x2 / b.getX2()) < epsilon)
             && (Math.abs(this.x1 / b.getX1() - this.x3 / b.getX3()) < epsilon) ){
        return new VectorSpace(0.0, 0.0, 0.0);
      }
      double v1 = this.x2 * b.getX3() - this.x3 * b.getX2();
      double v2 = - (this.x1 * b.getX3() - this.x3 * b.getX1());
      double v3 = this.x1 * b.getX2() - this.x2 * b.getX1();
      return new VectorSpace(v1, v2, v3);
  }
  public double getNormaVector(){
     return Math.sqrt(this.x1 * this.x1 + this.x2 * this.x2 + this.x3 * this.x3);
  }
  public static VectorSpace getRandomVector(double min, double max){
      double w1 = (max - min) * Math.random() + min;
      double w2 = (max - min) * Math.random() + min;
      double w3 = (max - min) * Math.random() + min;
      return new VectorSpace(w1, w2, w3);
  }
  public static VectorSpace[] getArrayRandomVectors(double min, double max, int n){
      VectorSpace[] arraysRandomVectors = new VectorSpace[n];
        for (int i = 0; i < n; i++){
        arraysRandomVectors[i] = VectorSpace.getRandomVector(min, max);
        }
      return arraysRandomVectors;
  }
}
