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
package com.github._1c_syntax.bsl.languageserver.diagnostics;

import org.eclipse.lsp4j.Diagnostic;
import com.github._1c_syntax.bsl.languageserver.providers.DiagnosticProvider;
import com.github._1c_syntax.bsl.languageserver.utils.RangeHelper;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class CommentedCodeDiagnosticTest extends AbstractDiagnosticTest<CommentedCodeDiagnostic> {

  CommentedCodeDiagnosticTest() {
    super(CommentedCodeDiagnostic.class);
  }

  @Test
  void runTest()
  {
    List<Diagnostic> diagnostics = getDiagnostics();

    assertThat(diagnostics).hasSize(10);
    assertThat(diagnostics.get(0).getRange()).isEqualTo(RangeHelper.newRange(0, 0, 6, 81));
    assertThat(diagnostics.get(1).getRange()).isEqualTo(RangeHelper.newRange(16, 4, 34, 16));
    assertThat(diagnostics.get(2).getRange()).isEqualTo(RangeHelper.newRange(36, 4, 42, 156));
    assertThat(diagnostics.get(3).getRange()).isEqualTo(RangeHelper.newRange(44, 4, 49, 16));
    assertThat(diagnostics.get(4).getRange()).isEqualTo(RangeHelper.newRange(59, 4, 65, 78));
    assertThat(diagnostics.get(5).getRange()).isEqualTo(RangeHelper.newRange(76, 0, 80, 18));
    assertThat(diagnostics.get(6).getRange()).isEqualTo(RangeHelper.newRange(82, 0, 82, 23));
    assertThat(diagnostics.get(7).getRange()).isEqualTo(RangeHelper.newRange(84, 0, 84, 46));
    assertThat(diagnostics.get(8).getRange()).isEqualTo(RangeHelper.newRange(87, 0, 106, 2));
    assertThat(diagnostics.get(9).getRange()).isEqualTo(RangeHelper.newRange(121, 0, 122, 24));
  }

  @Test
  void testConfigure() {

    Map<String, Object> configuration = DiagnosticProvider.getDefaultDiagnosticConfiguration(getDiagnosticInstance());
    configuration.put("threshold", 1f);
    getDiagnosticInstance().configure(configuration);

    List<Diagnostic> diagnostics = getDiagnostics();

    assertThat(diagnostics).hasSize(0);

  }
}