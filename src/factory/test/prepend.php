<?
unset($argv[0]);
foreach($argv as $a)
{
$f=fopen($a,'r');
$lines = 'package factory.factory200.laneManager;'."\n".fread($f,10000000);
echo $lines;
fclose($f);
$f = fopen($a,'w');
fwrite($f,$lines);
fclose($f);
}

?>