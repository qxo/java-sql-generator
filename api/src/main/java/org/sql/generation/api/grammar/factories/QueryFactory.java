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

package org.sql.generation.api.grammar.factories;

import org.sql.generation.api.grammar.builders.query.ColumnsBuilder;
import org.sql.generation.api.grammar.builders.query.FromBuilder;
import org.sql.generation.api.grammar.builders.query.GroupByBuilder;
import org.sql.generation.api.grammar.builders.query.OrderByBuilder;
import org.sql.generation.api.grammar.builders.query.QueryBuilder;
import org.sql.generation.api.grammar.builders.query.QuerySpecificationBuilder;
import org.sql.generation.api.grammar.builders.query.SimpleQueryBuilder;
import org.sql.generation.api.grammar.common.NonBooleanExpression;
import org.sql.generation.api.grammar.common.SetQuantifier;
import org.sql.generation.api.grammar.common.ValueExpression;
import org.sql.generation.api.grammar.literals.SQLFunctionLiteral;
import org.sql.generation.api.grammar.query.GroupByClause;
import org.sql.generation.api.grammar.query.Ordering;
import org.sql.generation.api.grammar.query.OrdinaryGroupingSet;
import org.sql.generation.api.grammar.query.QueryExpression;
import org.sql.generation.api.grammar.query.QueryExpressionBody;
import org.sql.generation.api.grammar.query.QueryExpressionBody.EmptyQueryExpressionBody;
import org.sql.generation.api.grammar.query.LimitSpecification;
import org.sql.generation.api.grammar.query.OffsetSpecification;
import org.sql.generation.api.grammar.query.QuerySpecification;
import org.sql.generation.api.grammar.query.RowDefinition;
import org.sql.generation.api.grammar.query.RowSubQuery;
import org.sql.generation.api.grammar.query.RowValueConstructor;
import org.sql.generation.api.grammar.query.SortSpecification;
import org.sql.generation.api.grammar.query.TableValueConstructor;
import org.sql.generation.api.vendor.SQLVendor;

/**
 * A factory, which creates builders and syntax elements for SQL queries ({@code SELECT} statements). This factory may
 * be obtained from {@link SQLVendor}.
 * 
 * @author Stanislav Muhametsin
 * @see SQLVendor
 * @see QueryExpression
 * @see QuerySpecification
 */
public interface QueryFactory
{

    /**
     * Creates new query, which has the specified body as an actual query.
     * 
     * @param body The actual query to use.
     * @return The new {@link QueryExpression}
     */
    public QueryExpression createQuery( QueryExpressionBody body );

    /**
     * Creates a builder to build query specifications ({@code SELECT} expressions).
     * 
     * @return The new {@link QuerySpecificationBuilder}.
     */
    public QuerySpecificationBuilder querySpecificationBuilder();

    /**
     * <p>
     * Creates a builder for the columns in {@code SELECT} expressions.
     * </p>
     * <p>
     * Calling this method is equivalent to calling {@link #columnsBuilder(SetQuantifier)} and passing
     * {@link SetQuantifier#ALL} as argument.
     * </p>
     * 
     * @return The new {@link ColumnsBuilder}.
     */
    public ColumnsBuilder columnsBuilder();

    /**
     * Creates a builder for columns in {@code SELECT} expressions, which has specified set quantifier initially.
     * 
     * @param setQuantifier The set quantifier to use.
     * @return The new {@link ColumnsBuilder}.
     */
    public ColumnsBuilder columnsBuilder( SetQuantifier setQuantifier );

    /**
     * <p>
     * Creates a builder to build queries with capability for {@code UNION}, {@code INTERSECT}, and {@code EXCEPT} set
     * operations.
     * </p>
     * <p>
     * Calling this method is equivalent in calling {@link #queryBuilder(QueryExpressionBody)} and passing
     * {@link EmptyQueryExpressionBody} as argument.
     * 
     * @return The new {@link QueryBuilder}.
     */
    public QueryBuilder queryBuilder();

    /**
     * Creates a builder to build queries with capability for {@code UNION}, {@code INTERSECT}, and {@code EXCEPT} set
     * operations.
     * 
     * @param query The initial query for builder.
     * @return The new {@link QueryBuilder}.
     */
    public QueryBuilder queryBuilder( QueryExpressionBody query );

    /**
     * Creates a builder for {@code GROUP BY} clause.
     * 
     * @return The new {@link GroupByBuilder}.
     */
    public GroupByBuilder groupByBuilder();

    /**
     * Creates a builder for {@code FROM} clause.
     * 
     * @return The new {@link FromBuilder}.
     */
    public FromBuilder fromBuilder();

    /**
     * Creates a new grouping element, which has some expressions as grouping columns.
     * 
     * @param expressions The expressions to use.
     * @return The new {@link OrdinaryGroupingSet}.
     * @see GroupByClause
     */
    public OrdinaryGroupingSet groupingElement( NonBooleanExpression... expressions );

    /**
     * Creates a new sort specification for {@code ORDER BY} clause.
     * 
     * @param expression The expression for column.
     * @param ordering The ordering to use.
     * @return The new {@link SortSpecification}.
     */
    public SortSpecification sortSpec( ValueExpression expression, Ordering ordering );

    /**
     * Creates a builder for {@code ORDER BY} clause.
     * 
     * @return The new {@link OrderByBuilder}.
     */
    public OrderByBuilder orderByBuilder();

    /**
     * Creates a builder for simple queries.
     * 
     * @return The new {@link SimpleQueryBuilder}.
     */
    public SimpleQueryBuilder simpleQueryBuilder();

    /**
     * Creates a new {@code VALUES} expression in query.
     * 
     * @param rows The rows for {@code VALUES} expression.
     * @return The new {@link TableValueConstructor}.
     * @see RowValueConstructor
     * @see RowSubQuery
     * @see RowDefinition
     */
    public TableValueConstructor values( RowValueConstructor... rows );

    /**
     * Creates a new subquery for a row for {@code VALUES} expression in query.
     * 
     * @param subQuery The query to return the row.
     * @return The new {@link RowSubQuery}.
     */
    public RowSubQuery rowSubQuery( QueryExpression subQuery );

    /**
     * Creates a new row for {@code VALUES} expression in query.
     * 
     * @param elements The elements for the row.
     * @return The new {@link RowDefinition}.
     */
    public RowDefinition row( ValueExpression... elements );

    /**
     * Returns a query for calling a SQL function with schema. The query is
     * {@code SELECT * FROM schemaName.functionName(params...)}.
     * 
     * @param schemaName The name of the schema where SQL function resides.
     * @param function The SQL function to call.
     * @return A query returning the results of calling SQL function.
     */
    public QueryExpression callFunction( String schemaName, SQLFunctionLiteral function );

    /**
     * Returns a query for calling a SQL function without a schema. The query is
     * {@code SELECT * FROM functionName(params...)}. Calling this method is equivalent to calling
     * {@link #callFunction(String, SQLFunctionLiteral)} and passing {@code null} as first argument.
     * 
     * @param function The function to call.
     * @return A query returning the results of calling SQL function.
     */
    public QueryExpression callFunction( SQLFunctionLiteral function );

    /**
     * Creates a new {@code OFFSET <n> ROWS} syntax element for query.
     * 
     * @param offset The offset amount.
     * @return A new {@code OFFSET <n> ROWS} syntax element.
     */
    public OffsetSpecification offset( NonBooleanExpression offset );

    /**
     * Creates a new {@code FETCH FIRST <n> ROWS ONLY} syntax element for query.
     * 
     * @param count The limit amount.
     * @return A new {@code FETCH FIRST <n> ROWS ONLY} syntax element.
     */
    public LimitSpecification limit( NonBooleanExpression count );
}
