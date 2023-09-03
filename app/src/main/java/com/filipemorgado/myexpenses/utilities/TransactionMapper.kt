    package com.filipemorgado.myexpenses.utilities

    import com.filipemorgado.myexpenses.db.TransactionEntity
    import com.filipemorgado.myexpenses.model.Transaction


    internal fun TransactionEntity.mapToLib(): Transaction {
        return Transaction(
            transactionType,
            category,
            description,
            amount,
            date,
        )
    }

    internal fun Transaction.mapToLib(): TransactionEntity {
        return TransactionEntity(
            transactionType,
            category,
            description,
            amount,
            date,
        )
    }