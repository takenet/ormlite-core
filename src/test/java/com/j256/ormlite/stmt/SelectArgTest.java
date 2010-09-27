package com.j256.ormlite.stmt;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;

import org.junit.Test;

import com.j256.ormlite.BaseOrmLiteCoreTest;

public class SelectArgTest extends BaseOrmLiteCoreTest {

	@Test(expected = SQLException.class)
	public void testGetBeforeSetValue() throws Exception {
		SelectArg selectArg = new SelectArg();
		selectArg.getValue();
	}

	@Test
	public void testSetValue() throws Exception {
		SelectArg selectArg = new SelectArg();
		Object foo = new Object();
		selectArg.setValue(foo);
		assertSame(foo, selectArg.getValue());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetColumnName() {
		SelectArg selectArg = new SelectArg();
		selectArg.getColumnName();
	}

	@Test
	public void testGetColumnNameOk() {
		SelectArg selectArg = new SelectArg();
		String name = "fwewfwef";
		selectArg.setMetaInfo(name, stringFieldType);
		assertSame(name, selectArg.getColumnName());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetColumnNameTwice() {
		SelectArg selectArg = new SelectArg();
		selectArg.setMetaInfo("1", numberFieldType);
		selectArg.setMetaInfo("2", numberFieldType);
	}

	@Test
	public void testSetNullValue() throws Exception {
		SelectArg selectArg = new SelectArg();
		selectArg.setValue(null);
		assertNull(selectArg.getValue());
	}

	@Test
	public void testForeignValue() throws Exception {
		SelectArg selectArg = new SelectArg();
		assertTrue(selectArg.toString().contains("unset arg"));
		BaseFoo value = new BaseFoo();
		String id = "dewpofjweee";
		value.id = id;
		selectArg.setValue(value);
		selectArg.setMetaInfo("id", foreignFieldType);
		assertTrue(selectArg.toString().contains("set arg"));
		assertTrue(selectArg + " wrong value", selectArg.toString().contains(id));
	}

	@Test
	public void testToString() throws Exception {
		SelectArg selectArg = new SelectArg();
		assertTrue(selectArg.toString().contains("unset arg"));
		String value = "fwefefewf";
		selectArg.setValue(value);
		assertTrue(selectArg.toString().contains("set arg"));
		assertTrue(selectArg.toString().contains(value));
	}
}
