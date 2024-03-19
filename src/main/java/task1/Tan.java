package task1;

import org.apache.commons.math3.fraction.BigFraction;

public class Tan {
    public static double calc(double x, int n) {
        if (x > Math.PI / 2) {
            while (x > Math.PI / 2) x -= Math.PI;
        }
        else if (x < -Math.PI / 2) {
            while (x < -Math.PI / 2) x += Math.PI;
        }

        if (x == Math.PI / 2) {
            return Double.POSITIVE_INFINITY;
        }
        else if (x == -Math.PI / 2) {
            return Double.NEGATIVE_INFINITY;
        }

        double eps = 0.1;

        double sum = x;
        double previousSum = Integer.MIN_VALUE;

        while (Math.abs(sum - previousSum) >= eps) {
            previousSum = sum;
            var decimal = bernouilli(2 * n);
            double bernouilliCoefficient = (double) decimal.getNumeratorAsLong() / decimal.getDenominatorAsLong();
            sum += (bernouilliCoefficient * (Math.pow(-4, n)) * (1 - Math.pow(4, n)) / fact(2L * n)) * (Math.pow(x, 2 * n - 1));
            n++;

        }
        return sum;
    }

    private static BigFraction bernouilli(int n) {
        BigFraction[] a = new BigFraction[n + 1];
        for (int m = 0; m <= n; m++) {
            a[m] = new BigFraction(1, (m + 1));
            for (int j = m; j >= 1; j--)
                a[j - 1] = (a[j - 1].subtract(a[j])).multiply(new BigFraction(j));
        }
        return a[0];
    }

    private static long fact(long x) {
        return (x == 1) ? 1 : fact(x - 1) * x;
    }
}
