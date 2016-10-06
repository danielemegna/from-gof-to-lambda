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
        double result = calculate(30000.00,
                new DefaultSalaryCalculator(),
                Taxes::generalTax,
                Taxes::regionalTax,
                Taxes::healthInsurance
        );
        System.out.println(result);
    }

    private static double calculate(double salary, DoubleUnaryOperator... operators) {
        DoubleUnaryOperator chain = DoubleUnaryOperator.identity();

        for (DoubleUnaryOperator operator : operators)
            chain = chain.andThen(operator);

        return chain.applyAsDouble(salary);
    }
}
