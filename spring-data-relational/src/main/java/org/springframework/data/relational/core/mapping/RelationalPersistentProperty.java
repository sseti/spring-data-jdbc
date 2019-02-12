/*
 * Copyright 2017-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.data.relational.core.mapping;

import org.springframework.data.mapping.PersistentProperty;
import org.springframework.lang.Nullable;

/**
 * A {@link PersistentProperty} with methods for additional JDBC/RDBMS related meta data.
 *
 * @author Jens Schauder
 * @author Oliver Gierke
 * @author Bastian Wilhelm
 */
public interface RelationalPersistentProperty extends PersistentProperty<RelationalPersistentProperty> {

	boolean isReference();

	/**
	 * Returns the name of the column backing this property.
	 *
	 * @return the name of the column backing this property.
	 */
	String getColumnName();

	/**
	 * The type to be used to store this property in the database. Multidimensional arrays are unwrapped to reflect a
	 * top-level array type (e.g. {@code String[][]} returns {@code String[]}).
	 *
	 * @return a {@link Class} that is suitable for usage with JDBC drivers.
	 * @see org.springframework.data.jdbc.support.JdbcUtil#sqlTypeFor(Class)
	 */
	Class<?> getColumnType();

	/**
	 * The SQL type constant used when using this property as a parameter for a SQL statement.
	 *
	 * @return Must not be {@code null}.
	 * @see java.sql.Types
	 */
	int getSqlType();

	@Override
	RelationalPersistentEntity<?> getOwner();

	String getReverseColumnName();

	@Nullable
	String getKeyColumn();

	/**
	 * Returns if this property is a qualified property, i.e. a property referencing multiple elements that can get picked
	 * by a key or an index.
	 */
	boolean isQualified();

	Class<?> getQualifierColumnType();

	/**
	 * Returns whether this property is an ordered property.
	 */
	boolean isOrdered();

	/**
	 * @return true, if the Property is an embedded value object, otherwise false.
	 */
	default boolean isEmbedded() {
		return false;
	};

	/**
	 * @return Prefix for embedded columns. If the column is not embedded the return value is null.
	 */
	@Nullable
	default String getEmbeddedPrefix() {
		return null;
	};
}
