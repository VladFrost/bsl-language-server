/*
 * This file is a part of BSL Language Server.
 *
 * Copyright © 2018-2019
 * Alexey Sosnoviy <labotamy@gmail.com>, Nikita Gryzlov <nixel2007@gmail.com> and contributors
 *
 * SPDX-License-Identifier: LGPL-3.0-or-later
 *
 * BSL Language Server is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3.0 of the License, or (at your option) any later version.
 *
 * BSL Language Server is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with BSL Language Server.
 */
package com.github._1c_syntax.bsl.languageserver.context.computer;

import com.github._1c_syntax.bsl.languageserver.context.DocumentContext;
import com.github._1c_syntax.bsl.languageserver.utils.RangeHelper;
import org.eclipse.lsp4j.Diagnostic;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.github._1c_syntax.bsl.languageserver.util.TestUtils.getDocumentContext;
import static org.assertj.core.api.Assertions.assertThat;

class DiagnosticIgnoranceComputerTest {

  @Test
  void testDiagnosticIgnorance() throws IOException {

    // given
    final DocumentContext documentContext =
      getDocumentContext("./src/test/resources/context/computer/DiagnosticIgnoranceComputerTest.bsl");

    List<Diagnostic> ignoredDiagnostics = new ArrayList<>();

    ignoredDiagnostics.add(createDiagnostic("SpaceAtStartComment", 4));
    ignoredDiagnostics.add(createDiagnostic("SemicolonPresence", 5));
    ignoredDiagnostics.add(createDiagnostic("RandomDiagnostic", 6));
    ignoredDiagnostics.add(createDiagnostic("SemicolonPresence", 13));
    ignoredDiagnostics.add(createDiagnostic("SpaceAtStartComment", 20));
    ignoredDiagnostics.add(createDiagnostic("SemicolonPresence", 21));
    ignoredDiagnostics.add(createDiagnostic("SemicolonPresence", 27));

    List<Diagnostic> notIgnoredDiagnostics = new ArrayList<>();
    notIgnoredDiagnostics.add(createDiagnostic("SpaceAtStartComment", 12));
    notIgnoredDiagnostics.add(createDiagnostic("RandomDiagnostic", 20));
    notIgnoredDiagnostics.add(createDiagnostic("SemicolonPresence", 29));

    // when
    Computer<DiagnosticIgnoranceComputer.Data> diagnosticIgnoranceComputer =
      new DiagnosticIgnoranceComputer(documentContext);
    DiagnosticIgnoranceComputer.Data data = diagnosticIgnoranceComputer.compute();

    // then
    assertThat(ignoredDiagnostics).allMatch(data::diagnosticShouldBeIgnored);
    assertThat(notIgnoredDiagnostics).noneMatch(data::diagnosticShouldBeIgnored);
  }

  private static Diagnostic createDiagnostic(String code, int line) {
    Diagnostic diagnostic = new Diagnostic();
    diagnostic.setCode(code);
    diagnostic.setRange(RangeHelper.newRange(line, 0, line, 0));

    return diagnostic;
  }
}