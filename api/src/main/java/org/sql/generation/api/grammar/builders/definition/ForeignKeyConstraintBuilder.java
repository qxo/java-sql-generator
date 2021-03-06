/*
 * Copyright (c) 2010, Stanislav Muhametsin. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package org.sql.generation.api.grammar.builders.definition;

import java.util.List;

import org.sql.generation.api.grammar.builders.AbstractBuilder;
import org.sql.generation.api.grammar.common.TableNameDirect;
import org.sql.generation.api.grammar.definition.table.ForeignKeyConstraint;
import org.sql.generation.api.grammar.definition.table.MatchType;
import org.sql.generation.api.grammar.definition.table.ReferentialAction;

/**
 * The builder for table constraint {@code FOREIGN KEY(source columns) REFERENCES table_name(target columns) etc....}).
 * 
 * @author Stanislav Muhametsin
 */
public interface ForeignKeyConstraintBuilder
    extends AbstractBuilder<ForeignKeyConstraint>
{

    /**
     * Adds source column names to this foreign key constraint.
     * 
     * @param columnNames The source column names to be added to this foreign key constraint.
     * @return This builder.
     */
    public ForeignKeyConstraintBuilder addSourceColumns( String... columnNames );

    /**
     * Adds target column names to this foreign key constraint.
     * 
     * @param columnNames The target column names to be added to this foreign key constraint.
     * @return This builder.
     */
    public ForeignKeyConstraintBuilder addTargetColumns( String... columnNames );

    /**
     * Sets the target table name for this foreign key constraint.
     * 
     * @param tableName The target table name for this foreign key constraint.
     * @return This builder.
     */
    public ForeignKeyConstraintBuilder setTargetTableName( TableNameDirect tableName );

    /**
     * Sets the match type for this foreign key constraint.
     * 
     * @param matchType The match type for this foreign key constraint.
     * @return This builder.
     * @see MatchType
     */
    public ForeignKeyConstraintBuilder setMatchType( MatchType matchType );

    /**
     * Sets the {@code ON UPDATE} action.
     * 
     * @param action The action to perform {@code ON UPDATE}.
     * @return This builder.
     * @see ReferentialAction
     */
    public ForeignKeyConstraintBuilder setOnUpdate( ReferentialAction action );

    /**
     * Sets the {@code ON DELETE} action.
     * 
     * @param action The action to perform {@code ON DELETE}.
     * @return This builder.
     * @see ReferentialAction
     */
    public ForeignKeyConstraintBuilder setOnDelete( ReferentialAction action );

    /**
     * Returns the source column names for this foreign key constraint.
     * 
     * @return The source column names for this foreign key constraint.
     */
    public List<String> getSourceColumns();

    /**
     * Returns the target column names for this foreign key constraint.
     * 
     * @return The target column names for this foreign key constraint.
     */
    public List<String> getTargetColumns();

    /**
     * Returns the target table name for this foreign key constraint.
     * 
     * @return The target table name for this foreign key constraint.
     */
    public TableNameDirect getTableName();

    /**
     * Returns the match type for this foreign key constraint.
     * 
     * @return The match type for this foreign key constraint.
     * @see MatchType
     */
    public MatchType getMatchType();

    /**
     * Returns the {@code ON UPDATE} action for this foreign key constraint.
     * 
     * @return The {@code ON UPDATE} action for this foreign key constraint.
     * @see ReferentialAction
     */
    public ReferentialAction getOnUpdate();

    /**
     * Returns the {@code ON DELETE} action for this foreign key constraint.
     * 
     * @return The {@code ON DELETE} action for this foreign key constraint.
     * @see ReferentialAction
     */
    public ReferentialAction getOnDelete();
}
