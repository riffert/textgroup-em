# textgroup-em (entity manager based persistence layer)
A comparison with same user interface like textgroup-sd but with entity manager based persistence layer<br>
<br>

<ul>
	<li>Deploy the application on a application server with property <b>hibernate.hbm2ddl.auto</b> set to <b>create</b> in <b>applicationContext.xml</b> file</li>
	<li>After startup, build the language table from <b>language.sql</b></li>
	<li>Restart with <b>validate</b> value for <b>hibernate.hbm2ddl.auto</b></li>
</ul>

Note : this application has no pages management like textgroup-sd, use only this last one if you plan to use it<br> 