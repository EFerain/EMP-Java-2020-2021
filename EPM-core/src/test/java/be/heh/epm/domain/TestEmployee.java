package be.heh.epm.domain;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class TestEmployee {

    private Employee employee;
    private PayCheck pc;

    @Before
    public void setUp() throws Exception {
        employee = new Employee(100, "toto", "av maistriau", "toto@gmail.com");
        LocalDate payDate = LocalDate.of(2019, 10, 2);
        pc = new PayCheck(payDate);
    }

    @Test
    public void createSalariedEmployee() {

        employee.setPayClassification(new SalariedClassification(1000));
        employee.setPayMethod(new DirectDepositMethod("ING", "be80-4444-444"));
        employee.setPaySchedule(new MonthlyPaymentSchedule());

        employee.payDay(pc);
        double pay = pc.getSalary();
        assertEquals(1000, pay, 0.01);

        PaymentSchedule ps = employee.getPaySchedule();
        assertTrue(ps instanceof MonthlyPaymentSchedule);

        PaymentMethod pm = employee.getPayMethod();
        assertEquals("direct deposit into ING : be80-4444-444", pm.toString());

    }

    @Test
    public void createHourlyEmployee() {

        employee.setPayClassification(new HourlyClassification(20.0));
        employee.setPayMethod(new MailMethod(employee.getMail()));
        employee.setPaySchedule(new WeeklyPaymentSchedule());

        LocalDate date = LocalDate.of(2019, 10, 1);
        LocalDate nextDate = LocalDate.of(2019, 10, 2);
        LocalDate dateOutside = LocalDate.of(2019, 9, 2);

        PaymentClassification classification = employee.getPayClassification();
        ((HourlyClassification) classification).addTimeCard(new TimeCard(date, 8.0));
        ((HourlyClassification) classification).addTimeCard(new TimeCard(nextDate, 10.0));
        ((HourlyClassification) classification).addTimeCard(new TimeCard(dateOutside, 8.0));

        employee.payDay(pc);
        double pay = pc.getSalary();

        assertEquals(380.0, pay, 0.01);

        PaymentSchedule ps = employee.getPaySchedule();
        assertTrue(ps instanceof WeeklyPaymentSchedule);

        PaymentMethod pm = employee.getPayMethod();
        assertEquals("mail : toto@gmail.com", pm.toString());

    }

    @Test
    public void monthlyPaymentSchedule() {
        employee.setPayClassification(new SalariedClassification(1000));
        employee.setPayMethod(new DirectDepositMethod("ING", "be80-4444-444"));
        employee.setPaySchedule(new MonthlyPaymentSchedule());

        LocalDate LastDayOfMonth = LocalDate.of(2019, 10, 31);

        assertTrue(employee.isDatePay(LastDayOfMonth));

    }

    @Test
    public void monthlyPaymentScheduleWrong() {
        employee.setPayClassification(new SalariedClassification(1000));
        employee.setPayMethod(new DirectDepositMethod("ING", "be80-4444-444"));
        employee.setPaySchedule(new MonthlyPaymentSchedule());

        LocalDate firstDayOfMonthWrong = LocalDate.of(2019, 10, 1);

        assertFalse(employee.isDatePay(firstDayOfMonthWrong));

    }

    @Test
    public void weeklyPaymentSchedule() {
        employee.setPayClassification(new SalariedClassification(1000));
        employee.setPayMethod(new DirectDepositMethod("ING", "be80-4444-444"));
        employee.setPaySchedule(new WeeklyPaymentSchedule());

        LocalDate fridayDate = LocalDate.of(2020, 10, 2);

        assertTrue(employee.isDatePay(fridayDate));

    }

    @Test
    public void weeklyPaymentScheduleWrong() {
        employee.setPayClassification(new SalariedClassification(1000));
        employee.setPayMethod(new DirectDepositMethod("ING", "be80-4444-444"));
        employee.setPaySchedule(new WeeklyPaymentSchedule());

        LocalDate MondayDate = LocalDate.of(2020, 10, 5);

        assertFalse(employee.isDatePay(MondayDate));

    }

    // ======== Commission ========
    @Test
    public void createCommissionEmployee()
    {
        employee.setPayClassification(new CommissionClassification(1000, 10));
        employee.setPayMethod(new CashMethod());
        employee.setPaySchedule(new TwoWeekPaySchedule());

        LocalDate date = LocalDate.of(2019,10,1);
        LocalDate nextDate = LocalDate.of(2019,10,2);

        PaymentClassification classification = employee.getPayClassification();
        ((CommissionClassification)classification).addSalesReceipt(new SalesReceipt(date, 200));
        ((CommissionClassification)classification).addSalesReceipt(new SalesReceipt(nextDate, 150));

        employee.payDay(pc);
        double pay = pc.getSalary();

        assertEquals(1350, pay, 0.01);

        PaymentSchedule ps = employee.getPaySchedule();
        assertTrue(ps instanceof TwoWeekPaySchedule);

        PaymentMethod pm = employee.getPayMethod();
        assertEquals("cash", pm.toString());
    }

    @Test
    public void alacon()
    {
        LocalDate date = LocalDate.of(2020, 10, 2);
        LocalDate date1 = LocalDate.of(2020, 10, 9);
        LocalDate date2 = LocalDate.of(2020, 10, 16);
        LocalDate date3 = LocalDate.of(2020, 10, 23);
        LocalDate date4 = LocalDate.of(2020, 10, 30);
        LocalDate date5 = LocalDate.of(2020, 11, 6);
        LocalDate date6 = LocalDate.of(2020, 11, 13);
        LocalDate date7 = LocalDate.of(2020, 11, 20);
        LocalDate date8 = LocalDate.of(2020, 11, 27);
        LocalDate date9 = LocalDate.of(2020, 12, 4);
        LocalDate date10 = LocalDate.of(2020, 10, 1);

        TwoWeekPaySchedule bidule = new TwoWeekPaySchedule();

        assertTrue(!bidule.isDatePay(date));
        assertTrue(bidule.isDatePay(date1));
        assertTrue(!bidule.isDatePay(date2));
        assertTrue(bidule.isDatePay(date3));
        assertTrue(!bidule.isDatePay(date4));
        assertTrue(bidule.isDatePay(date5));
        assertTrue(!bidule.isDatePay(date6));
        assertTrue(bidule.isDatePay(date7));
        assertTrue(!bidule.isDatePay(date8));
        assertTrue(!bidule.isDatePay(date9));
        assertTrue(!bidule.isDatePay(date10));
    }
}
