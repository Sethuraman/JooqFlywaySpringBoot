package csmart.api.config.db;

import com.fasterxml.jackson.databind.JsonNode;
import org.jooq.*;
import org.jooq.impl.DSL;

import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.sql.Types;

import static org.jooq.tools.Convert.convert;

public class PostgresJSONJacksonJsonNodeBinding
        implements Binding<Object, JsonNode> {

    @Override
    public Converter<Object, JsonNode> converter() {
        return new PostgresJSONJacksonJsonNodeConverter();
    }

    @Override
    public void sql(BindingSQLContext<JsonNode> ctx) throws SQLException {

        // This ::json cast is explicitly needed by PostgreSQL:
        ctx.render().visit(DSL.val(ctx.convert(converter()).value())).sql("::json");
    }

    @Override
    public void register(BindingRegisterContext<JsonNode> ctx) throws SQLException {
        ctx.statement().registerOutParameter(ctx.index(), Types.VARCHAR);
    }

    @Override
    public void set(BindingSetStatementContext<JsonNode> ctx) throws SQLException {
        ctx.statement().setString(
                ctx.index(),
                convert(ctx.convert(converter()).value(), String.class));
    }

    @Override
    public void get(BindingGetResultSetContext<JsonNode> ctx) throws SQLException {
        ctx.convert(converter()).value(ctx.resultSet().getString(ctx.index()));
    }

    @Override
    public void get(BindingGetStatementContext<JsonNode> ctx) throws SQLException {
        ctx.convert(converter()).value(ctx.statement().getString(ctx.index()));
    }

    // The below methods aren't needed in PostgreSQL:

    @Override
    public void set(BindingSetSQLOutputContext<JsonNode> ctx) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    @Override
    public void get(BindingGetSQLInputContext<JsonNode> ctx) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }
}