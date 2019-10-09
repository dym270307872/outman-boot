package cn.dyaoming.log.daos;

import cn.dyaoming.log.entitys.TestLog;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The Interface TestLogDao.
 * @author abel
 */
public interface TestLogDao extends JpaRepository<TestLog, Long> {


}