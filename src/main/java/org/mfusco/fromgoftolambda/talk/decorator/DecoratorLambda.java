package org.mfusco.fromgoftolambda.talk.decorator;

import java.util.function.DoubleUnaryOperator;

public class DecoratorLambda {

    public static class DefaultSalaryCalculator implements DoubleUnaryOperator {

        @Override
        public double applyAsDouble(double grossAnnual) {
            return grossAnnual / 12;
        }
    }

    public static class GeneralTaxDecorator implements DoubleUnaryOperator {

        @Override
        public double applyAsDouble(double salary) {
            return Taxes.generalTax(salary);
        }
    }

    public static class RegionalTaxDecorator implements DoubleUnaryOperator {

        @Override
        public double applyAsDouble(double salary) {
            return Taxes.regionalTax(salary);
        }
    }

    public static class HealthInsuranceDecorator implements DoubleUnaryOperator {

        @Override
        public double applyAsDouble(double salary) {
            return Taxes.healthInsurance(salary);
        }
    }

    public static void main(String[] args) {
        double result = new DefaultSalaryCalculator()
                .andThen(new GeneralTaxDecorator())
                .andThen(new RegionalTaxDecorator())
                .andThen(new HealthInsuranceDecorator())
                .applyAsDouble(30000.00);

        System.out.println(result);
    }
}
