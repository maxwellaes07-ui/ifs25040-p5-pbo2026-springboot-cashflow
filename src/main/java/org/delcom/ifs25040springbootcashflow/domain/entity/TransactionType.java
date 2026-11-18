package org.delcom.ifs25040springbootcashflow.domain.entity;

/** Jenis transaksi keuangan. */
public enum TransactionType {
    INCOME("Pemasukan"),
    EXPENSE("Pengeluaran");

    private final String label;

    TransactionType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
