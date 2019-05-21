<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link href="../../assets/css/bootstrap.min.css" rel="stylesheet" />
    <title>帮助-交易规则</title>
</head>
<body>
<h3>交易规则</h3>

<ol>
    <li><p>模拟炒股接受24小时委托</p></li>
    <li><p>支持上交所和深交所两大交易所上市的A股股票，不支持新股   </p></li>
    <li><p>交易手续费为万分之5    </p></li>
    <li><p>成交规则：撮合系统每10秒进行一次交易撮合，处理买单、卖单、撤单。
        <br/>成交价格： <br/>
        按照交易所公布的最新成交价撮合，而不是按照买卖盘的价格撮合。
        <br/>买入时：根据卖一~卖五价进行撮合
        <br/>卖出时：根据卖一~卖五价进行撮合
        <br/>成交数量： <br/>
        模拟炒股的撮合考虑了真实交易的成交数量，即使委托价格合适，如果没有成交量，也不会成交。
        如果真实交易的成交数量小于委托数量，则部分成交，仅撮合真实交易的成交数量，剩余的委托仍保留在撮合队列，等待新的成交明细。
        （真实交易的成交数量可以从真实行情中看到）。 <br/>对于没有成交的委托，或者部分成交的委托，可以撤单。
        当天的委托如果没有成交，收市以后自动作废，不参加下一交易日的撮合。   </p>
    </li>
    <li><p>涨跌停限制： 委托价格应限制在当日开盘价上下浮动的百分之十价格范围内 </p></li>
</ol>
</body>
</html>
