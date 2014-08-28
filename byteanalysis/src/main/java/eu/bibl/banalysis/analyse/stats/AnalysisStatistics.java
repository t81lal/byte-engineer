package eu.bibl.banalysis.analyse.stats;

/**
 * @author sc4re
 */
public final class AnalysisStatistics {

    private int analysedClasses, analysedMethods, analysedFields;

    public int getAnalysedClasses() {
        return analysedClasses;
    }

    public int getAnalysedMethods() {
        return analysedMethods;
    }

    public int getAnalysedFields() {
        return analysedFields;
    }

    public void incrementClasses(int amount) {
        analysedClasses += amount;
    }

    public void incrementMethods(int amount) {
        analysedMethods += amount;
    }

    public void incrementFields(int amount) {
        this.analysedFields += amount;
    }

    public void setClasses(int classes) {
        this.analysedClasses = classes;
    }

    public void setMethods(int methods) {
        this.analysedMethods = methods;
    }

    public void setFields(int fields) {
        this.analysedFields = fields;
    }

    public AnalysisStatistics combine(AnalysisStatistics other) {
        AnalysisStatistics stats = new AnalysisStatistics();
        stats.setClasses(getAnalysedClasses());
        stats.setMethods(getAnalysedMethods());
        stats.setFields(getAnalysedFields());

        stats.incrementClasses(other.getAnalysedClasses());
        stats.incrementMethods(other.getAnalysedMethods());
        stats.incrementFields(other.getAnalysedFields());
        return stats;
    }
}
