.MODEL SMALL
.CODE
Inicio:
mov Ax, @Data
mov Ds, Ax
push Cx
mov Al, 0
mov Di, offset Fin
inc Di
mov byte ptr [Di], 3
mov Di, offset Fin +2
call ConvertirAscii
pop Cx
SUMERGIR0:
push Cx
mov Si, offset Fin
call ConvertirDecimal
push Ax
mov Bl, 1
pop Ax
cmp Al, Bl
pop Cx
je FSUMERGIR0
SUMERGIR1:
mov Dx, offset Mge0
call EXHALAR 
mov Si, offset A
inc Si
mov byte ptr [Si], 0
mov Dx, offset A
call LEER
mov Dx, offset Mge1
call EXHALAR 
mov Dx, offset Salto
call EXHALAR 
push Cx
mov Si, offset A                   
36   = 
call ConvertirDecimal
push Ax
mov Bl, 0
pop Ax
cmp Al, Bl
pop Cx
je FSI0
SI0:
push Cx
mov Si, offset A
call ConvertirDecimal
push Ax
mov Bl, 1
pop Ax
cmp Al, Bl
pop Cx
jnae FSI1
push Cx
mov Si, offset A
call ConvertirDecimal
push Ax
mov Bl, 100
pop Ax
cmp Al, Bl
pop Cx
jnbe FSI1
SI1:
mov Dx, offset Mge2
call EXHALAR 
mov Si, offset B
inc Si
mov byte ptr [Si], 0
mov Dx, offset B
call LEER
mov Dx, offset Mge3
call EXHALAR 
mov Dx, offset Salto
call EXHALAR 
push Cx
mov Si, offset B
call ConvertirDecimal
push Ax
mov Bl, 0
pop Ax
cmp Al, Bl
pop Cx
je FSI2
SI2:
push Cx
mov Si, offset B
call ConvertirDecimal
push Ax
mov Si, offset A
call ConvertirDecimal
mov Bl, Al
pop Ax
cmp Al, Bl
pop Cx
je FSI3
SI3:
push Cx
mov Si, offset B
call ConvertirDecimal
push Ax
mov Bl, 1
pop Ax
cmp Al, Bl
pop Cx
jnae FSI4
push Cx
mov Si, offset B
call ConvertirDecimal
push Ax
mov Bl, 100
pop Ax
cmp Al, Bl
pop Cx
jnbe FSI4
SI4:
mov Dx, offset Mge4
call EXHALAR 
mov Si, offset C
inc Si
mov byte ptr [Si], 0
mov Dx, offset C
call LEER
mov Dx, offset Mge5
call EXHALAR 
mov Dx, offset Salto
call EXHALAR 
push Cx
mov Si, offset C
call ConvertirDecimal
push Ax
mov Bl, 0
pop Ax
cmp Al, Bl
pop Cx
je FSI5
SI5:
push Cx
mov Si, offset C
call ConvertirDecimal
push Ax
mov Si, offset A
call ConvertirDecimal
mov Bl, Al
pop Ax
cmp Al, Bl
pop Cx
je FSI6
SI6:
push Cx
mov Si, offset C
call ConvertirDecimal
push Ax
mov Si, offset B
call ConvertirDecimal
mov Bl, Al
pop Ax
cmp Al, Bl
pop Cx
je FSI7
SI7:
push Cx
mov Si, offset C
call ConvertirDecimal
push Ax
mov Bl, 1
pop Ax
cmp Al, Bl
pop Cx
jnae FSI8
push Cx
mov Si, offset C
call ConvertirDecimal
push Ax
mov Bl, 100
pop Ax
cmp Al, Bl
pop Cx
jnbe FSI8
SI8:
push Cx
mov Si, offset A
call ConvertirDecimal
push Ax
mov Si, offset B
call ConvertirDecimal
mov Bl, Al
pop Ax
cmp Al, Bl
pop Cx
jnb FSI9
push Cx
mov Si, offset A
call ConvertirDecimal
push Ax
mov Si, offset C
call ConvertirDecimal
mov Bl, Al
pop Ax
cmp Al, Bl
pop Cx
jnb FSI9
SI9:
push Cx
mov Si, offset A
call ConvertirDecimal
mov Di, offset LimiI
inc Di
mov byte ptr [Di], 3
mov Di, offset LimiI +2
call ConvertirAscii
pop Cx
jmp FSINO9
FSI9:
push Cx
mov Si, offset B
call ConvertirDecimal
push Ax
mov Si, offset A
call ConvertirDecimal
mov Bl, Al
pop Ax
cmp Al, Bl
pop Cx
jnb FSI10
push Cx
mov Si, offset B
call ConvertirDecimal
push Ax
mov Si, offset C
call ConvertirDecimal
mov Bl, Al
pop Ax
cmp Al, Bl
pop Cx
jnb FSI10
SI10:
push Cx
mov Si, offset B
call ConvertirDecimal
mov Di, offset LimiI
inc Di
mov byte ptr [Di], 3
mov Di, offset LimiI +2
call ConvertirAscii
pop Cx
jmp FSINO10
FSI10:
push Cx
mov Si, offset C
call ConvertirDecimal
mov Di, offset LimiI
inc Di
mov byte ptr [Di], 3
mov Di, offset LimiI +2
call ConvertirAscii
pop Cx
FSINO10:
FSINO9:
push Cx
mov Si, offset A
call ConvertirDecimal
push Ax
mov Si, offset B
call ConvertirDecimal
mov Bl, Al
pop Ax
cmp Al, Bl
pop Cx
jna FSI11
push Cx
mov Si, offset A
call ConvertirDecimal
push Ax
mov Si, offset C
call ConvertirDecimal
mov Bl, Al
pop Ax
cmp Al, Bl
pop Cx
jna FSI11
SI11:
push Cx
mov Si, offset A
call ConvertirDecimal
mov Di, offset LimiS
inc Di
mov byte ptr [Di], 3
mov Di, offset LimiS +2
call ConvertirAscii
pop Cx
jmp FSINO11
FSI11:
push Cx
mov Si, offset B
call ConvertirDecimal
push Ax
mov Si, offset A
call ConvertirDecimal
mov Bl, Al
pop Ax
cmp Al, Bl
pop Cx
jna FSI12
push Cx
mov Si, offset B
call ConvertirDecimal
push Ax
mov Si, offset C
call ConvertirDecimal
mov Bl, Al
pop Ax
cmp Al, Bl
pop Cx
jna FSI12
SI12:
push Cx
mov Si, offset B
call ConvertirDecimal
mov Di, offset LimiS
inc Di
mov byte ptr [Di], 3
mov Di, offset LimiS +2
call ConvertirAscii
pop Cx
jmp FSINO12
FSI12:
push Cx
mov Si, offset C
call ConvertirDecimal
mov Di, offset LimiS
inc Di
mov byte ptr [Di], 3
mov Di, offset LimiS +2
call ConvertirAscii
pop Cx
FSINO12:
FSINO11:
push Cx
mov Si, offset LimiS
call ConvertirDecimal
push Ax
mov Al, 2
mov Bl, Al
pop Ax
call DIVIDIR
mov Di, offset Mitad +2
call ConvertirAscii
mov byte ptr Mitad+1, 3
pop Cx
push Cx
mov Si, offset Mitad
call ConvertirDecimal
push Ax
mov Al, 1
mov Bl, Al
pop Ax
mov Ah, Bl
call SUMA
mov Di, offset Mitad +2
call ConvertirAscii
mov byte ptr Mitad+1, 3
pop Cx
push Cx
xor Ax, Ax
mov Si, offset LimiI
call ConvertirDecimal
push Ax
mov Si, offset Mitad
call ConvertirDecimal
mov Dl, Al
pop Ax
mov Ah, Dl
push Ax
xor Cx, Cx
mov Cl, Ah
sub Cl, Al
add Cl, 1
pop Bx
push Cx
mov Ax, Bx
mov Di, offset AuxInm +2
call ConvertirAscii
mov byte ptr AuxInm+1, 3
pop Cx
INMERSION0:
push Cx
push Cx
mov Si, offset AuxInm
call ConvertirDecimal
push Ax
mov Bl, 1
pop Ax
cmp Al, Bl
pop Cx
jna FSI13
SI13:
push Cx
mov Al, 0
mov Di, offset Cont
inc Di
mov byte ptr [Di], 3
mov Di, offset Cont +2
call ConvertirAscii
pop Cx
push Cx
xor Ax, Ax
mov Al, 1
push Ax
mov Si, offset AuxInm
call ConvertirDecimal
mov Dl, Al
pop Ax
mov Ah, Dl
push Ax
xor Cx, Cx
mov Cl, Ah
sub Cl, Al
add Cl, 1
pop Bx
push Cx
mov Ax, Bx
mov Di, offset Divisor +2
call ConvertirAscii
mov byte ptr Divisor+1, 3
pop Cx
INMERSION1:
push Cx
push Cx
mov Si, offset AuxInm
call ConvertirDecimal
push Ax
mov Si, offset Divisor
call ConvertirDecimal
mov Bl, Al
pop Ax
call DIVIDIR
mov Al, AuxResi
mov Di, offset Aux +2
call ConvertirAscii
mov byte ptr Aux+1, 3
pop Cx
push Cx
mov Si, offset Aux
call ConvertirDecimal
push Ax
mov Bl, 0
pop Ax
cmp Al, Bl
pop Cx
jne FSI14
SI14:
push Cx
mov Si, offset Cont
call ConvertirDecimal
push Ax
mov Al, 1
mov Bl, Al
pop Ax
mov Ah, Bl
call SUMA
mov Di, offset Cont +2
call ConvertirAscii
mov byte ptr Cont+1, 3
pop Cx
FSI14:
mov Si, offset Divisor
call ConvertirDecimal
inc Al
mov Di, offset Divisor +2
call ConvertirAscii
pop Cx
loop INMERSION1
pop Cx
push Cx
mov Si, offset Cont
call ConvertirDecimal
push Ax
mov Bl, 2
pop Ax
cmp Al, Bl
pop Cx
jne FSI15
SI15:
mov Dx, offset AuxInm +2
call EXHALAR
mov Dx, offset Mge6
call EXHALAR 
FSI15:
FSI13:
mov Si, offset AuxInm
call ConvertirDecimal
inc Al
mov Di, offset AuxInm +2
call ConvertirAscii
pop Cx
loop INMERSION0
pop Cx
mov Dx, offset Mge7
call EXHALAR 
mov Dx, offset Salto
call EXHALAR 
FSI8:
FSI7:
FSI6:
jmp FSINO5
FSI5:
push Cx
mov Al, 1
mov Di, offset Fin
inc Di
mov byte ptr [Di], 3
mov Di, offset Fin +2
call ConvertirAscii
pop Cx
FSINO5:
FSI4:
FSI3:
jmp FSINO2
FSI2:
push Cx
mov Al, 1
mov Di, offset Fin
inc Di
mov byte ptr [Di], 3
mov Di, offset Fin +2
call ConvertirAscii
pop Cx
FSINO2:
FSI1:
jmp FSINO0
FSI0:
push Cx
mov Al, 1
mov Di, offset Fin
inc Di
mov byte ptr [Di], 3
mov Di, offset Fin +2
call ConvertirAscii
pop Cx
FSINO0:
jmp SUMERGIR0
FSUMERGIR0:
mov Ax, 4C00h
int 21h
ConvertirDecimal Proc
xor Cx, Cx
mov Cl, [Si+1]
cmp Cl, 0
je FinConvDec
add Si, 2
xor Bx, Bx
ConvDecCiclo:
mov Al, 10
mul Bl
mov Bl, Al
mov Al, [Si]
sub Al, 30h
add Bl, Al
inc Si
loop ConvDecCiclo
mov Al, Bl
FinConvDec:
ret
ConvertirDecimal EndP
ConvertirAscii Proc
mov Ah, 0
mov Bl, 100
div Bl
add Al, 30h
mov [Di], Al
inc Di
mov Al, Ah
mov Ah, 0
mov Bl, 10
div Bl
add Al, 30h
mov [Di], Al
inc Di
mov Al, Ah
add Al, 30h
mov [Di], Al
mov byte ptr [Di+1], 24h
ret
ConvertirAscii EndP
EXHALAR Proc
mov Ah, 09h
int 21h
ret
EXHALAR EndP
LEER Proc
mov Ah, 0Ah
int 21h
ret
LEER EndP
DIVIDIR Proc
xor Ah, Ah
div Bl
mov AuxResi, Ah
ret
DIVIDIR EndP
SUMA Proc
add Al, Ah
mov Ah, 0
ret
SUMA EndP
.DATA
A db 8,?,8 dup (24h)
B db 8,?,8 dup (24h)
C db 8,?,8 dup (24h)
Mitad db 8,?,8 dup (24h)
Fin db 8,?,8 dup (24h)
Aux db 8,?,8 dup (24h)
LimiI db 8,?,8 dup (24h)
LimiS db 8,?,8 dup (24h)
AuxInm db 8,?,8 dup (24h)
Divisor db 8,?,8 dup (24h)
Cont db 8,?,8 dup (24h)
Mge0 db "Ingrese el valor de A $"
Salto db 10, 13, 24h
Mge1 db " $"
Mge2 db "Ingrese el valor de B $"
Mge3 db " $"
Mge4 db "Ingrese el valor de C $"
Mge5 db " $"
AuxResi db ?
Mge6 db "  $"
Mge7 db " $"
.STACK
END Inicio
