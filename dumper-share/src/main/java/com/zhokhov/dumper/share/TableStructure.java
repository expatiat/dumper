package com.zhokhov.dumper.share;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.core.annotation.Introspected;

import java.util.List;

@Introspected
public class TableStructure {

    private final String tableName;

    private final String primaryKeyConstraint;

    // list of column names
    private final List<String> primaryKeys;

    private final List<Column> columns;

    @JsonCreator
    public TableStructure(
            @JsonProperty("tableName") String tableName,
            @JsonProperty("primaryKeyConstraint") String primaryKeyConstraint,
            @JsonProperty("primaryKeys") List<String> primaryKeys,
            @JsonProperty("columns") List<Column> columns
    ) {
        this.tableName = tableName;
        this.primaryKeyConstraint = primaryKeyConstraint;
        this.primaryKeys = primaryKeys;
        this.columns = columns;
    }

    public String getTableName() {
        return tableName;
    }

    public String getPrimaryKeyConstraint() {
        return primaryKeyConstraint;
    }

    public List<String> getPrimaryKeys() {
        return primaryKeys;
    }

    public List<Column> getColumns() {
        return columns;
    }

    public static class Column {

        private final String name;
        private final String type;
        private final boolean userDefinedType;
        private final List<ForeignKey> foreignKeys;

        @JsonCreator
        public Column(
                @JsonProperty("name") String name,
                @JsonProperty("type") String type,
                @JsonProperty("userDefined") boolean userDefinedType,
                @JsonProperty("foreignKeys") List<ForeignKey> foreignKeys
        ) {
            this.name = name;
            this.type = type;
            this.userDefinedType = userDefinedType;
            this.foreignKeys = foreignKeys;
        }

        public String getName() {
            return name;
        }

        public String getType() {
            return type;
        }

        public boolean isUserDefinedType() {
            return userDefinedType;
        }

        public List<ForeignKey> getForeignKeys() {
            return foreignKeys;
        }

    }

    public static class ForeignKey {

        private final String referenceTableName;
        private final String referenceColumnName;

        @JsonCreator
        public ForeignKey(
                @JsonProperty("referenceTableName") String referenceTableName,
                @JsonProperty("referenceColumnName") String referenceColumnName
        ) {
            this.referenceTableName = referenceTableName;
            this.referenceColumnName = referenceColumnName;
        }

        public String getReferenceTableName() {
            return referenceTableName;
        }

        public String getReferenceColumnName() {
            return referenceColumnName;
        }

    }

}
