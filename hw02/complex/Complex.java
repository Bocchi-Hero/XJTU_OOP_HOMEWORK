package hw02.complex;

public class Complex {
    private double real = 0.0;
    private double imag = 0.0;
    private final double EPSILON = 1e-8;

    public Complex() {

    }

    public Complex(double real, double imag) {
        this.real = real;
        this.imag = imag;
    }

    public double getReal() {
        return real;
    }

    public void setReal(double real) {
        this.real = real;
    }

    public double getImag() {
        return imag;
    }

    public void setImag(double imag) {
        this.imag = imag;
    }

    public void setValue(double real, double imag) {
        this.real = real;
        this.imag = imag;
    }

    @Override
    public String toString() {
        return real + " + " + imag + "i";
    }

    public boolean isReal() {
        return imag == 0;
    }

    public boolean isImaginary() {
        return real == 0 && imag != 0;
    }

    public boolean equals(double real, double imag) {
        return Math.abs(this.real - real) < EPSILON && Math.abs(this.imag - imag) < EPSILON;
    }

    public boolean equals(Complex anotherComplex) {
        return equals(anotherComplex.getReal(), anotherComplex.getImag());
    }

    public double abs() {
        return Math.hypot(real, imag);
    }

    public Complex add(Complex right) {
        return new Complex(real + right.getReal(), imag + right.getImag());
    }

    public Complex subtract(Complex right) {
        return new Complex(real - right.getReal(), imag - right.getImag());
    }

    public Complex multiply(Complex right) {
        return new Complex(real * right.getReal() - imag * right.getImag(), real * right.getImag() + imag * right.getReal());
    }

    public Complex divide(Complex right) {
        double coefficient = right.getReal() * right.getReal() + right.getImag() * right.getImag();

        if (coefficient < EPSILON) {
            return new Complex(Double.NaN, Double.NaN);
        }

        double newReal = (real * right.getReal() + imag * right.getImag()) / coefficient;
        double newImag = (imag * right.getReal() - real * right.getImag()) / coefficient;

        return new Complex(newReal, newImag);
    }
}
