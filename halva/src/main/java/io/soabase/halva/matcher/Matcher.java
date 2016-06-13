/**
 * Copyright 2016 Jordan Zimmerman
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.soabase.halva.matcher;

import io.soabase.halva.any.AnyType;
import io.soabase.halva.tuple.Tuple;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Matcher<ARG> extends Getter<ARG> implements CasesBase<ARG, Matcher<ARG>>
{
    public static <ARG> Matcher<ARG> match(ARG arg)
    {
        return new Matcher<>(arg);
    }

    public static <ARG> Partial<ARG> partial(Class<ARG> marker)
    {
        return new PartialImpl<>();
    }

    public static <ARG> Partial<ARG> partial(AnyType<ARG> marker)
    {
        return new PartialImpl<>();
    }

    <T> Matcher<ARG> register(Tuple fields, Guard guard, Supplier<T> proc)
    {
        ExtractObject extracter = new ExtractObject(fields, guard);
        addEntry(new Entry<>(extracter, proc));
        return this;
    }

    @Override
    public <T> Matcher<ARG> caseOf(Tuple fields, Guard guard, Supplier<T> proc)
    {
        return register(fields, guard, proc);
    }

    @Override
    public <T> Matcher<ARG> caseOf(Tuple fields, Supplier<T> proc)
    {
        return register(fields, null, proc);
    }

    @Override
    public <T> Matcher<ARG> caseOf(Object lhs, Supplier<T> proc)
    {
        return register(Tuple.Tu(lhs), null, proc);
    }

    @Override
    public Matcher<ARG> caseOfUnit(Object lhs, Runnable proc)
    {
        return register(Tuple.Tu(lhs), null, wrap(proc));
    }

    @Override
    public <T> Matcher<ARG> caseOf(Object lhs, Guard guard, Supplier<T> proc)
    {
        return register(Tuple.Tu(lhs), guard, proc);
    }

    @Override
    public <T> Matcher<ARG> caseOfUnit(Tuple lhs, Guard guard, Runnable proc)
    {
        return register(lhs, guard, wrap(proc));
    }

    @Override
    public <T> Matcher<ARG> caseOfUnit(Object lhs, Guard guard, Runnable proc)
    {
        return register(Tuple.Tu(lhs), guard, wrap(proc));
    }

    @Override
    public <T> Matcher<ARG> caseOfUnit(Tuple lhs, Runnable proc)
    {
        return register(lhs, null, wrap(proc));
    }

    @Override
    public <T> Matcher<ARG> caseOfTest(Predicate<ARG> tester, Supplier<T> proc)
    {
        return register(Tuple.Tu(tester), null, proc);
    }

    @Override
    public Matcher<ARG> caseOfTestUnit(Predicate<ARG> tester, Runnable proc)
    {
        return register(Tuple.Tu(tester), null, wrap(proc));
    }

    @Override
    public <T> Matcher<ARG> caseOf(Supplier<T> proc)
    {
        setDefault(proc);
        return this;
    }

    @Override
    public Matcher<ARG> caseOfUnit(Runnable proc)
    {
        setDefault(wrap(proc));
        return this;
    }

    Matcher(ARG arg)
    {
        super(arg);
    }

    private <T> Supplier<T> wrap(Runnable proc)
    {
        return () -> { proc.run(); return null; };
    }
}
