package models;

import java.text.NumberFormat;
import java.util.List;
import java.util.Random;

public class Person {

    private int netWorth = 0;
    private int health = 100;
    private int age = 18;
    private int children = 0;
    private final NumberFormat money = NumberFormat.getCurrencyInstance();
    private int strength = 0, intellect = 0, creativity = 0;
    private Boolean education = false, isMarried = false, hasPrivilege = false;
    private Careers career = Careers.PASSION;
    private String jobTitle = null;
    private Person partner = null;
    private String name;
    private String moneyChange;
    private String healthChange;

    public Person() {
        money.setMaximumFractionDigits(0);
    }



    public Person(String name, int initialWorth) {
        this.name = name;
        this.netWorth = initialWorth;
    }

    public int getHealthPoints() {
        return this.health;
    }

    public int getNetWorth() {
        return netWorth;
    }

    public void setNetWorth(int netWorth) {
        this.netWorth = netWorth;
    }

    public String getPrettyNetWorth() {
        return money.format(netWorth);
    }

    public boolean hasEducation() {
        return education;
    }

    public String getCategoryValue(int index) {

        if (index < 0)
            index = 0;

        List<String> fieldValues = List.of(
                career.toString().toLowerCase(),
                education.toString(),
                getPartnerStatus(),
                hasPrivilege.toString(),
                Boolean.toString(health > 50),
                Boolean.toString(children > 0)
        );
        return fieldValues.get(index);
    }

    public String getPartnerStatus() {
        String partnerStatus = "single";

        if (this.partner != null)
            partnerStatus = isMarried ? "married" : "partner";

        return partnerStatus;
    }

    public void addMoney(int amountToAdd) {
        double randModifier = new Random().nextDouble() * (1.25d - .75d) + .75d;
        int modifiedAmountToAdd = (int) (amountToAdd * randModifier);
        netWorth += modifiedAmountToAdd;
        final String msg = String.format("You have %s %s from your choice", (amountToAdd < 0 ? "lost" : "gained"), money.format(modifiedAmountToAdd));
        System.out.println(msg);
        moneyChange = msg;
    }

    public void addHealth(int value) {
        health += value;
        if (health > 100)
            health = 100;
        String gained = value < 0 ? "lost" : "gained";
        String msg = String.format("You have %s %d health", gained, value);
        System.out.println(msg);
        healthChange = msg;

    }

    public void addPartner(int value) {
        if (partner == null) {
            partner = new Person("Sam", value);

            final String msg = String.format("You have a new partner named %s", partner.name);
            System.out.println(msg);
        }

    }

    public void breakUp(int value) {
        this.partner = null;
        this.isMarried = false;
        System.out.println("You and Sam have broken up.");
    }

    public void marryPartner(int value) {
        if (partner != null) {
            this.setMarried(true);
            partner.setMarried(true);
            System.out.println("You have married your partner, Sam");
        }
    }

    public void setMarried(boolean b) {
        this.isMarried = b;
    }

    public void addChild(int value) {
        if (value < 0){
            value = 0;
        }
        children += value;
        String some = value > 1 ? "children" : "child";
        String msg = String.format("You have gained %d %s", value, some);
        System.out.println(msg);
    }

    public void removeChild() {
        children = 0;
    }
    public void changeCareer(int value) {
        String oldCareer = career.name();
        career = Careers.values()[value];
        final String msg = String.format("Your career has changed from %s to %s", oldCareer, career.toString());
        System.out.println(msg);
    }

    public void setEducation(boolean b) {
        this.education = b;
    }

    public void setCareer(Careers career) {
        this.career = career;
    }

    public Careers getCareer() {
        return this.career;
    }


    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public void addAge(int i) {
        age += i;

        if (age > 50) {
            Random rand = new Random();
            int amountHealthToDecrease = -(rand.nextInt(15) + 1);
            System.out.println("You are getting older and losing health.");
            addHealth(amountHealthToDecrease);
        }

        String msg = String.format("You are now %d years old.", age);
        System.out.println(msg);
    }

    public String getName() {
        return name;
    }

    public int getChildren() {
        return this.children;
    }

    public Person getPartner() {
        return partner;
    }

    public boolean isMarried() {
        return isMarried;
    }

    public String addSalary() {
        int amountToAdd = career.getSalaryAmount() * 5;
        double educationMultiplier = hasEducation() ? 1.5 : 1;
        double incomeMultiplier = getIncomeMultiplier();
        int sum = (int) (amountToAdd * educationMultiplier * incomeMultiplier);
        int oldNetWorth = netWorth;
        netWorth = sum + netWorth;

        String netWorthSummary = "Your current net worth: " + money.format(netWorth);
        System.out.println(netWorthSummary);

        return "\nYou have earned " + money.format(sum) + " in the last 5 years from your job.\n" +
                "\nNet worth breakdown: " +
                "\nBase yearly salary: " + money.format(career.getSalaryAmount()) +
                "\nYearly salary * 5 years: " + money.format(amountToAdd) +
                "\nEducation Multiplier: " + educationMultiplier +
                "\nIncome Multiplier from " + getAttributeFromCareer() + ": " + incomeMultiplier +
                "\nTotal: (Yearly Salary * 5 years * education multiplier * income multiplier): " + money.format(sum) + " + Previous net worth: " + money.format(oldNetWorth) + "=" + money.format(netWorth);
    }

    private String getAttributeFromCareer() {
        switch (career) {
            case DANGER:
                return "strength";
            case KNOWLEDGE:
                return "intellect";
            case PASSION:
                return "creativity";
            default:
                return "none";
        }
    }

    public double getIncomeMultiplier() {
        //1 - 1.5

        switch (career) {
            case DANGER:
                return (10.0 + strength) / 10;
            case KNOWLEDGE:
                return (10.0 + intellect) / 10;
            case PASSION:
                return (10.0 + creativity) / 10;
            default:
                return 1;
        }
    }

    public void setName(String playerName) {
        this.name = playerName;
    }

    public void addCreativity(int i) {
        this.creativity += i;
    }

    public void addIntellect(int i) {
        this.intellect += i;
    }

    public void addStrength(int i) {
        this.strength += i;
    }

    public int getStrength() {
        return this.strength;
    }

    public int getIntellect() {
        return this.intellect;
    }

    public int getCreativity() {
        return this.creativity;
    }

    public int getAge() {
        return this.age;
    }

    public void setPrivilege(boolean b) {
        this.hasPrivilege = b;
    }

    public void removePartner() {

        partner = null;

    }

    public void addDivorce() {

        removePartner();
        setMarried(false);

    }

    //added setter to use when loading a saved game
    public void setAge(int age) {
        this.age = age;
    }

    //added setter to use when loading a saved game
    public void setHealth(int health) {
        this.health = health;
    }
    public void setStrength(int strength) {
        this.strength = strength;
    }

    public void setIntellect(int intellect) {
        this.intellect = intellect;
    }

    public void setCreativity(int creativity) {
        this.creativity = creativity;
    }

    public String getMoneyChange() {
        return moneyChange;
    }

    public String getHealthChange() {
        return healthChange;
    }
}
