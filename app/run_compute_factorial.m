clc
clear all
close all
x=input('enter the number whose factorial u want to find')
fact=1;
for n=1:x
    fact=fact*n;
end;
disp('factorial of a given number is:')
disp(fact)
