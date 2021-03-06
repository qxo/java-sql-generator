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

package org.sql.generation.implementation.grammar.query.joins;

import org.sql.generation.api.common.NullArgumentException;
import org.sql.generation.api.grammar.common.ColumnNameList;
import org.sql.generation.api.grammar.query.joins.NamedColumnsJoin;
import org.sql.generation.implementation.transformation.spi.SQLProcessorAggregator;

/**
 * 
 * @author Stanislav Muhametsin
 */
public class NamedColumnsJoinImpl extends JoinSpecificationImpl<NamedColumnsJoin>
    implements NamedColumnsJoin
{

    private final ColumnNameList _columnNames;

    public NamedColumnsJoinImpl( SQLProcessorAggregator processor, ColumnNameList columnNames )
    {
        this( processor, NamedColumnsJoin.class, columnNames );
    }

    protected NamedColumnsJoinImpl( SQLProcessorAggregator processor, Class<? extends NamedColumnsJoin> implClass,
        ColumnNameList columnNames )
    {
        super( processor, implClass );

        NullArgumentException.validateNotNull( "column names", columnNames );

        this._columnNames = columnNames;
    }

    public ColumnNameList getColumnNames()
    {
        return this._columnNames;
    }

    @Override
    protected boolean doesEqual( NamedColumnsJoin another )
    {
        return this._columnNames.equals( another.getColumnNames() );
    }
}
