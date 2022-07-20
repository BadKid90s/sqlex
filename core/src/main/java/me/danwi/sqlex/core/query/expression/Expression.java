package me.danwi.sqlex.core.query.expression;

import java.util.Arrays;

public interface Expression {
    /**
     * 表达式转换成SQL片段
     *
     * @return SQL判断
     */
    String toSQL();

    //预处理参数
    static ParameterExpression arg(Object value) {
        return new ParameterExpression(value);
    }

    //字面量
    static LiteralExpression lit(Object value) {
        return new LiteralExpression(value);
    }

    //函数调用
    static FunctionCallExpression func(String name, Expression... args) {
        return new FunctionCallExpression(name, Arrays.asList(args));
    }

    //原生SQL
    static RawExpression sql(String rawSQL) {
        return new RawExpression(rawSQL);
    }

    //region 逻辑运算
    static NotExpression not(Expression exp) {
        return new NotExpression(exp);
    }

    default BinaryExpression and(Expression right) {
        return new BinaryExpression("and", this, right);
    }

    default BinaryExpression or(Expression right) {
        return new BinaryExpression("or", this, right);
    }
    //endregion

    //region 关系运算
    default BinaryExpression eq(Expression right) {
        return new BinaryExpression("=", this, right);
    }

    default BinaryExpression ne(Expression right) {
        return new BinaryExpression("<>", this, right);
    }

    default BinaryExpression gt(Expression right) {
        return new BinaryExpression(">", this, right);
    }

    default BinaryExpression gte(Expression right) {
        return new BinaryExpression(">=", this, right);
    }

    default BinaryExpression lt(Expression right) {
        return new BinaryExpression("<", this, right);
    }

    default BinaryExpression lte(Expression right) {
        return new BinaryExpression("<=", this, right);
    }

    default InExpression in(Iterable<Expression> set) {
        return new InExpression(this, set);
    }

    default NotExpression notIn(Iterable<Expression> set) {
        return not(this.in(set));
    }

    default LikeExpression like(Expression right) {
        return new LikeExpression(this, right);
    }

    default NotExpression notLike(Expression right) {
        return not(this.like(right));
    }

    default IsNullExpression isNull() {
        return new IsNullExpression(this);
    }

    default NotExpression isNotNull() {
        return not(this.isNull());
    }
    //endregion

    //region 算术运算
    default BinaryExpression add(Expression right) {
        return new BinaryExpression("+", this, right);
    }

    default BinaryExpression sub(Expression right) {
        return new BinaryExpression("-", this, right);
    }

    default BinaryExpression mul(Expression right) {
        return new BinaryExpression("*", this, right);
    }

    default BinaryExpression div(Expression right) {
        return new BinaryExpression("/", this, right);
    }
    //endregion

    //region 时间函数
    static FunctionCallExpression now() {
        return func("now");
    }

    static FunctionCallExpression currentTimestamp() {
        return func("current_timestamp");
    }

    static FunctionCallExpression dateFormat(Expression date, Expression format) {
        return func("date_format", date, format);
    }

    static FunctionCallExpression dateFormat(Expression date, String format) {
        return func("date_format", date, lit(format));
    }
    //endregion
}
