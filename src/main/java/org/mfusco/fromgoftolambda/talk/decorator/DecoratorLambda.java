package org.mfusco.fromgoftolambda.talk.decorator;

import java.util.function.DoubleUnaryOperator;

public class DecoratorLambda {

    public static class DefaultSalaryCalculator implements DoubleUnaryOperator {

        @Override
        public double applyAsDouble(double grossAnnual) {
            return grossAnnual / 12;
        }
    }

    public static void main(String[] args) {
        double result = new DefaultSalaryCalculator()
                .andThen(Taxes::generalTax)
                .andThen(Taxes::regionalTax)
                .andThen(Taxes::healthInsurance)
                .applyAsDouble(30000.00);

        System.out.println(result);
    }
}
