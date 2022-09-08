package com.revature.controllers;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.revature.dao.ReimbursementDao;
import com.revature.models.Reimbursement;

public class ReimbursementControllerTest {
    ReimbursementDao rd = new ReimbursementDao();
    @Test
    public void testCreateRei() {
        Reimbursement rei = new Reimbursement("Fifth", 5000, "Food");
        assertTrue(rei != null);
    }
}
