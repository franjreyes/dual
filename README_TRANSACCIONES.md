# Transacciones
## Qué es una transacción
Una transacción es un conjunto de acciones que deben realizarse de forma conjunta, si alguna de ellas no se completa no lo hace ninguna.

### Ejemplo
Si reservamos un libro en una biblioteca deben realizarse una serie de operaciones:
- Que el libro se encuentre en la biblioteca
- Que el libro esté disponible
- Que el usuario esté registrado en el sistema
- Retirar el libro
- Entregar el libro

En términos de base de datos, si el último paso no se completa, ésta quedaría en un estado inconsistente.

Para evitar estos casos existen las transacciones, si alguna de las acciones no se puede realizar todo se deshace a su estado inicial antes de la transacción.

Estos posibles errores son exponenciales a la simultaneidad del acceso a la información.

***

## Propiedades ACID
Estas son las características de una transacción:
- Atomicity (atomicidad): un cambio debe completarse en su totalidad o no modificar nada en absoluto.
- Consistency (consistencia): la base de datos debe quedar en un estado válido.
- Isolation (aislamiento): un cambio no debe afectar a otros cambios que se estén ejecutando al mismo tiempo sobre la base de datos.
- Durability (durabilidad): el cambio debe persistir, aunque se produzcan fallos en la base de datos o el sistema completo. 

## Commit y Rollback
Una transacción abierta (datos sucios) puede finalizar de dos formas:
- Si todo ha ido correctamente se realiza el **commit** para persistir los cambios y así dejar la base de datos actualizada en un estado consistente.
- Cualquier acción que no se pueda completar correctamente provoca un **rollback** y anula cualquier cambio realizado por el resto de acciones. La base de datos queda en el estado previo antes de la transacción

## @Transactional

Excepciones: Commit y Rollback
Spring es el encargado de hacer el commit de la transacción, de forma automática, al finalizar la misma sin errores. 
Ante un error de tipo RuntimeException, Spring hará rollback automáticamente de la transacción, incluso aunque el programador capture la excepción. Si el error que se lanza es de tipo Exception, Spring no hará rollback. 
Al igual que muchas cosas, estos detalles son configurables. Podemos hacer a través de la configuración que ante ciertos tipos de excepciones runtime no se haga rollback automático. Justo el comportamiento contrario podemos aplicar para las excepciones de tipo excepción, se puede programar que ante ciertas excepciones de este tipo se haga un rollback automático. 


***
#### Enlaces de interés
- https://www.baeldung.com/java-transactions
- https://www.baeldung.com/spring-transactional-propagation-isolation
- https://www.apascualco.com/spring-boot/spring-transactional/
- https://danielme.com/2023/02/22/curso-spring-data-jpa-transacciones-propagacion-excepciones/