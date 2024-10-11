package com.db2.PortefeuilleService.dao;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class ContratDAO {
    private final JdbcTemplate jdbcTemplate;

    public ContratDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public List<Map<String, Object>> findAll() {
        return jdbcTemplate.queryForList("select * from prtb001  Limit 10 ");
    }




    public List<Map<String, Object>> findByIDAndNUMCNT(String cin, String numCNT) {
        return jdbcTemplate.queryForList(
                "SELECT * " +
                        "FROM AMIDATA.PRTB001, AMIDATA.PETB01, AMIDATA.PR002 " +
                        "WHERE AMIDATA.PRTB001.NATCLT = AMIDATA.PETB01.CNAT " +
                        "AND AMIDATA.PRTB001.IDCLT = AMIDATA.PETB01.NUMPERS " +
                        "AND AMIDATA.PRTB001.CODPROD = AMIDATA.PR002.CODPROD " +
                        "AND AMIDATA.PRTB001.SITUAT IN ('E', 'R') " +
                        "AND AMIDATA.PRTB001.CODPROD = '684' " +
                        "AND AMIDATA.PETB01.ID = ? " +
                        "AND AMIDATA.PRTB001.NUMCNT = ?",
                cin, numCNT
        );
    }
    public List<Map<String, Object>> findContratByIDAndNUMCNT(String cin, String numCNT) {
        String sql = "SELECT * " +
                "FROM AMIDATA.SMTB11 " +
                "JOIN AMIDATA.PRTB001 ON AMIDATA.SMTB11.NUMCNT = AMIDATA.PRTB001.NUMCNT " +
                "JOIN AMIDATA.PETB01 ON AMIDATA.PRTB001.NATCLT = AMIDATA.PETB01.CNAT " +
                "AND AMIDATA.PRTB001.IDCLT = AMIDATA.PETB01.NUMPERS " +
                "JOIN AMIDATA.PR002 ON AMIDATA.PRTB001.CODPROD = AMIDATA.PR002.CODPROD " +
                "WHERE AMIDATA.PRTB001.SITUAT IN ('E', 'R') " +
                "AND AMIDATA.PETB01.ID = ? " +
                "AND AMIDATA.PRTB001.CODPROD = '684' " +
                "AND AMIDATA.PRTB001.NUMCNT = ? " +
                "AND AMIDATA.SMTB11.REF_PM = (" +
                "  SELECT MAX(REF_PM) " +
                "  FROM AMIDATA.SMTB11 " +
                "  JOIN AMIDATA.PRTB001 ON AMIDATA.SMTB11.NUMCNT = AMIDATA.PRTB001.NUMCNT " +
                "  WHERE AMIDATA.PRTB001.SITUAT IN ('E', 'R') " +
                "  AND AMIDATA.PRTB001.CODPROD = '684' " +
                "  AND AMIDATA.PRTB001.NUMCNT = ? " +
                ")";

        return jdbcTemplate.queryForList(sql, cin, numCNT, numCNT);
    }
    public List<Map<String, Object>> findContratsByID(String cin) {
        String sql = "WITH RankedContracts AS (" +
                "    SELECT AMIDATA.SMTB11.*, AMIDATA.PRTB001.*, AMIDATA.PETB01.*, AMIDATA.PR002.*, " +
                "INTEGER("+
                        "ROUND("+
                                "((DAYS(DATE(TIMESTAMP_FORMAT(CAST(fincnt AS VARCHAR(8)), 'YYYYMMDD'))) -"+
                                        "DAYS(DATE(TIMESTAMP_FORMAT(CAST(debcnt AS VARCHAR(8)), 'YYYYMMDD')))) / 365.25))) AS duree,"+
                "           ROW_NUMBER() OVER (PARTITION BY AMIDATA.PRTB001.NUMCNT ORDER BY AMIDATA.SMTB11.REF_PM DESC) AS rn " +
                "    FROM AMIDATA.SMTB11 " +
                "    JOIN AMIDATA.PRTB001 ON AMIDATA.SMTB11.NUMCNT = AMIDATA.PRTB001.NUMCNT " +
                "    JOIN AMIDATA.PETB01 ON AMIDATA.PRTB001.NATCLT = AMIDATA.PETB01.CNAT " +
                "        AND AMIDATA.PRTB001.IDCLT = AMIDATA.PETB01.NUMPERS " +
                "    JOIN AMIDATA.PR002 ON AMIDATA.PRTB001.CODPROD = AMIDATA.PR002.CODPROD " +
                "    WHERE AMIDATA.PRTB001.SITUAT IN ('E', 'R') " +
                "      AND AMIDATA.PETB01.ID = ? " +
                "      AND AMIDATA.PRTB001.CODPROD = '684' " +
                ") " +
                "SELECT * " +
                "FROM RankedContracts " +
                "WHERE rn = 1";

        return jdbcTemplate.queryForList(sql, cin);
    }

    public List<Map<String, Object>> QuittanceByNumcnt(String numCNT) {
        return jdbcTemplate.queryForList(
                "SELECT * " +
                        "FROM AMIDATA.GPTB01 " +
                        "JOIN AMIDATA.PETB01 ON AMIDATA.GPTB01.NATCLT = AMIDATA.PETB01.CNAT " +
                        "    AND AMIDATA.GPTB01.IDCLT = AMIDATA.PETB01.NUMPERS " +
                        "JOIN AMIDATA.PR002 AS PR ON AMIDATA.GPTB01.CODPROD = PR.CODPROD " +
                        "WHERE AMIDATA.GPTB01.NUMCNT = ? " +
                        "  AND AMIDATA.GPTB01.STATQUIT IN ('0', '1') " +
                        "ORDER BY AMIDATA.GPTB01.DATECREA DESC",
                numCNT
        );
    }





}
