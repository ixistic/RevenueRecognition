package db;

/**
 * Created by ixistic on 1/24/2017 AD.
 */

import extensions.MfDate;
import extensions.Money;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Gateway {
    private DBHelper db;
    private static final String findContractStatement =
            "SELECT * FROM contracts c, products p " +
                    "WHERE ID = ? AND c.product = p.ID";
    private static final String insertRecognitionStatement =
            "INSERT INTO revenueRecognitions VALUES (?, ?, ?)";

    public Gateway() {
        db = DBHelper.getInstance();
        db.establishConnection();
    }
    public ResultSet findContract (long contractID) throws SQLException {
        PreparedStatement stmt = db.prepareStatement(findContractStatement);
        stmt.setLong(1, contractID);
        ResultSet result = stmt.executeQuery();
        return result;
    }

    public void insertRecognition (long contractID, Money amount, MfDate asof)
            throws SQLException {
        PreparedStatement stmt = db.prepareStatement(insertRecognitionStatement);
        stmt.setLong(1, contractID);
        stmt.setBigDecimal(2, amount.getAmount());
//        stmt.setDate(3, asof.toSqlDate());
        stmt.executeUpdate();
    }

}
