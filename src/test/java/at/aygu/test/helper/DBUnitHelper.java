package at.aygu.test.helper;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;

import org.dbunit.DataSourceDatabaseTester;
import org.dbunit.dataset.xml.XmlDataSet;
import org.dbunit.operation.DatabaseOperation;

/**
 * DBUnit helper class to use in test cases.
 * @author guersel
 *
 */
public class DBUnitHelper {
	
	/**
	 * Initialize the database with test data from the given fixtures file.
	 * @param dbTester dbunit test class
	 * @param pathToFixture path to fixtures file
	 * @throws Exception if any error occurs
	 */
	public static void initDBWithFixtures(final DataSourceDatabaseTester dbTester, final String pathToFixture) throws Exception {
		URL url = Thread.currentThread().getContextClassLoader().getResource(pathToFixture);
		dbTester.setDataSet(new XmlDataSet(new FileInputStream(new File(url.toURI()))));
		dbTester.setSetUpOperation(DatabaseOperation.CLEAN_INSERT);
		dbTester.onSetup();
	}
	
	/**
	 * Remove all test data from the database.
	 * @param dbTester dbunit test class
	 * @throws Exception if any error occurs
	 */
	public static void resetDB(final DataSourceDatabaseTester dbTester) throws Exception {
		dbTester.setTearDownOperation(DatabaseOperation.DELETE_ALL);
		dbTester.onTearDown();
	}
}
