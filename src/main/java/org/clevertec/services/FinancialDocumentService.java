package org.clevertec.services;

public interface FinancialDocumentService {
    String savePDF(String financialDocument, String path);

    String saveTXT(String financialDocument, String path);
}
