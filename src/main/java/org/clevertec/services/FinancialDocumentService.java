package org.clevertec.services;

import org.clevertec.domain.AbstractFinancialDocument;

public interface FinancialDocumentService {
    String savePDF(AbstractFinancialDocument document, String path);

    String saveTXT(AbstractFinancialDocument document, String path);
}
