package com.ccic.service;

import com.ccic.config.domain.Result;
import com.ccic.config.domain.ResultSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;


public class BaseService extends ApplicationObjectSupport {
    protected final Logger log = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    protected TransactionTemplate transactionTemplate;

    /**
     * @return the transactionTemplate
     */
    public TransactionTemplate getTransactionTemplate() {
        return transactionTemplate;
    }

    /**
     * @param transactionTemplate the transactionTemplate to set
     */
    public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
        this.transactionTemplate = transactionTemplate;
    }

    /**
     * @param action
     * @return
     * @description 执行事务
     */
    protected Boolean executeTransaction(TransactionCallback<Boolean> action) {
        try {
            return (Boolean) transactionTemplate.execute(action);
        } catch (Throwable e) {
            log.error("Execute transaction failed, and transaction rollback ", e);
        }

        return Boolean.FALSE;
    }

    /**
     * @param action
     * @return
     * @description 执行事务
     */
    protected Result executeTransactionToResult(TransactionCallback<Result> action) {
        try {
            return (Result) transactionTemplate.execute(action);
        } catch (Throwable e) {
            log.error("Execute transaction failed, and transaction rollback ", e);
        }
        return new ResultSupport();
    }

}
