/**
 * Copyright (C) 2010-2017 Gordon Fraser, Andrea Arcuri and EvoSuite
 * contributors
 *
 * This file is part of EvoSuite.
 *
 * EvoSuite is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published
 * by the Free Software Foundation, either version 3.0 of the License, or
 * (at your option) any later version.
 *
 * EvoSuite is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with EvoSuite. If not, see <http://www.gnu.org/licenses/>.
 */
package org.evosuite.symbolic.solver;

import com.examples.with.different.packagename.solver.TestCaseTokenizer;
import org.evosuite.symbolic.TestCaseBuilder;
import org.evosuite.symbolic.expr.Constraint;
import org.evosuite.testcase.DefaultTestCase;
import org.evosuite.testcase.variable.VariableReference;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Map;

import static org.evosuite.symbolic.solver.TestSolver.solve;

public class TestSolverTokenizer {

	private static DefaultTestCase buildTestTokenizer()
			throws SecurityException, NoSuchMethodException {
		TestCaseBuilder tc = new TestCaseBuilder();
		VariableReference string0 = tc.appendStringPrimitive("Here is Ramon");
	
		Method method = TestCaseTokenizer.class.getMethod("test",
				String.class);
		tc.appendMethod(null, method, string0);
		return tc.getDefaultTestCase();
	}

	public static Map<String, Object> testStringTokenizer(Solver solver)
			throws SecurityException, NoSuchMethodException,
			SolverTimeoutException {
	
		DefaultTestCase tc = buildTestTokenizer();
		Collection<Constraint<?>> constraints = DefaultTestCaseConcolicExecutor
				.execute(tc);
		Map<String, Object> solution = solve(solver,constraints);
		return solution;
	}

}
