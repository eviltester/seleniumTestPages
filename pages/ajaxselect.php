<?php
/* millisecond  * millisecond sleep value e.g. 5000000 = 5000 milliseconds = 5 seconds */
usleep(500000); 
if ($_GET['id'] == 1) {
  echo <<<HERE_DOC
[ {optionValue: 0, optionDisplay: 'Javascript'}, 
  {optionValue:1, optionDisplay: 'VBScript'}, 
  {optionValue:2, optionDisplay: 'Flash'}]
HERE_DOC;
} else if ($_GET['id'] == 2) {
  echo <<<HERE_DOC
[{optionValue:10, optionDisplay: 'C++'}, 
  {optionValue:11, optionDisplay: 'Assembler'}, 
  {optionValue:12, optionDisplay: 'C'},
  {optionValue:13, optionDisplay: 'Visual Basic'}]
HERE_DOC;
} else if ($_GET['id'] == 3) {
  echo <<<HERE_DOC
[{optionValue:20, optionDisplay: 'Cobol'}, 
{optionValue:21, optionDisplay:'Fortran'},
{optionValue:22, optionDisplay:'C++'},
{optionValue:23, optionDisplay:'Java'}]
HERE_DOC;
}?>
