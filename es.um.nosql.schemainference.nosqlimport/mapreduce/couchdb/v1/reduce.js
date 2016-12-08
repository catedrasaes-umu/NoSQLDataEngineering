function (key, values, rereduce)
{
	if (rereduce)
		return values;
	else
		return values[0];
}
